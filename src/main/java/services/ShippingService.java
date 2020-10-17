package services;

import common.Utils;
import entity.shipping.ShopWeightOverShipRules;
import entity.shipping.ShopWeightShipRules;
import pojo.Basket;
import pojo.BasketItem;
import pojo.ShippingCosts;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ShippingService {

    private Basket basket;
    private EntityManager em;

    public ShippingService() {
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ShippingCosts getTotalWeightShippingCosts()
    {
        // query table  shipping_region_zips to get region and then ZONE
        // if result is empty query  shipping_zones_regions to get the ZONE
        // finally get shipping rule from shipping_zones table based on shipping class and zone
        Long zoneId = 0L;
        Utils.setEm(this.em);
        ShippingCosts c = new ShippingCosts();
        try {

            List<Object> zoneIdByZipObj = this.getEm().createNativeQuery("SELECT szr.zone_id AS zoneId FROM `shipping_region_zips` srzp\n" +
                    "JOIN shipping_zones_regions szr ON srzp.zone_region_id = szr.region_id " +
                    "WHERE srzp.code = :zipcode LIMIT 1 ")
                    .setParameter("zipcode", this.getBasket().getShipTo().getPostCode())
                    .getResultList();

            if (zoneIdByZipObj.size() == 0) {
                List<Object> zoneIdByRegion = this.getEm().createNativeQuery("SELECT zone_id " +
                        " FROM shipping_zones_regions " +
                        " WHERE region_id = :regionId LIMIT 1 ")
                        .setParameter("regionId", this.getBasket().getShipTo().getRegionId().getId())
                        .getResultList();
                if (zoneIdByRegion.size() > 0) {
                    zoneId = Long.parseLong(zoneIdByRegion.get(0).toString());

                }
            } else {
                zoneId = Long.parseLong(zoneIdByZipObj.get(0).toString());
            }
            System.out.println("zoneId" + zoneId);
            System.out.println("regionId" + this.getBasket().getShipTo().getRegionId().getId());
            System.out.println("zip" + this.getBasket().getShipTo().getPostCode());
            System.out.println("shopID" + this.getBasket().getShop().getId());

            // group products by shipping class
            Set<Long> shipClassesIds = new HashSet<>();
            for (BasketItem itm : basket.getItems()) {
                shipClassesIds.add(itm.getShipClassId());
            }
            BigDecimal classWeight;
            BigDecimal costTotal = new BigDecimal(0);

            Iterator<Long> itKlassId = shipClassesIds.iterator();
            while (itKlassId.hasNext()) {
                Long checkedKlassId = itKlassId.next();

                classWeight = new BigDecimal(0);
                for (BasketItem itm : basket.getItems()) {

                    if (itm.getShipClassId() == checkedKlassId) {
                        BigDecimal produktGewicht = Utils.getProductWeight(itm.getProd().getId());
                        produktGewicht = produktGewicht.multiply(new BigDecimal(itm.getQuantity()));
                        classWeight = classWeight.add(produktGewicht);
                    }
                }
                System.out.println("TOTAL WEIGHT of this class" + classWeight);
                Double varos = classWeight.doubleValue();

                List<ShopWeightShipRules> weightRules = this.getEm().createQuery("SELECT w FROM " +
                        " ShopWeightShipRules w " +
                        " JOIN w.shopId sh " +
                        " JOIN w.shippingClassId klass " +
                        " JOIN w.zoneId z " +
                        "  WHERE w.active = 1 AND z.id = :zoneId " +
                        "  AND klass.id = :klassId AND sh.id = :shopId")
                        .setParameter("zoneId", zoneId)
                        .setParameter("klassId", checkedKlassId)
                        .setParameter("shopId", this.getBasket().getShop().getId())
                        .getResultList();
                System.out.println("NORMAL WEIGHT RESULT " + weightRules.size());
                for (ShopWeightShipRules wRul : weightRules) {
                    Double upperValue = wRul.getOverThanKg().doubleValue();
                    Double lowerValue = wRul.getLessThanKg().doubleValue();

                    boolean lowerLimitOK = (varos > lowerValue) || (varos >= lowerValue && wRul.isLessEqual());
                    boolean upperLimitOK = (varos < upperValue) || (varos <= upperValue && wRul.isOverEqual());

                    if (wRul.isLessThanInfinity()) {
                        upperLimitOK = true;
                    }

                    if (lowerLimitOK && upperLimitOK) {
                        costTotal = costTotal.add(wRul.getBaseCost());
                        break;
                    }
                }

                // what if order is eg 15 kilos and there are two rules ,one for 5 kilos and one for 8 kilos

                List<ShopWeightOverShipRules> overWeightRules = this.getEm().createQuery("SELECT w FROM " +
                        " ShopWeightOverShipRules w " +
                        " JOIN w.shopId sh " +
                        " JOIN w.shippingClassId klass " +
                        " JOIN w.zoneId z " +
                        "  WHERE w.active = 1 AND z.id = :zoneId " +
                        "  AND klass.id = :klassId AND sh.id = :shopId" +
                        " AND w.overTotalWeight >= :kg " +
                        " ORDER BY w.overTotalWeight DESC")
                        .setParameter("zoneId", zoneId)
                        .setParameter("kg", varos)
                        .setParameter("klassId", checkedKlassId)
                        .setParameter("shopId", this.getBasket().getShop().getId())
                        .getResultList();
                if (overWeightRules.size() > 0) {
                    //  Double overLimit = overWeightRules.get(0).getOverTotalWeight().doubleValue();
                    //   Double extraWeight =  varos - overLimit;
                    BigDecimal overLimit = classWeight.subtract(overWeightRules.get(0).getOverTotalWeight());
                    costTotal = costTotal.add(overLimit.multiply(overWeightRules.get(0).getCharge()));
                }
            }

            // convert to selected currency ....
            c.setShipCost(costTotal);
            return c;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return c;
        }
    }
}
