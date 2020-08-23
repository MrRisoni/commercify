package services;

import dto.TopCategory;
import entity.*;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import pojo.Basket;
import pojo.BasketItem;
import pojo.ShippingCosts;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

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

    public ShippingCosts getTotalShippingCosts()
    {
        // query table  shipping_region_zips to get region and then ZONE
        // if result is empty query  shipping_zones_regions to get the ZONE
        // finally get shipping rule from shipping_zones table based on shipping class and zone
        Long zoneId = 0L;
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

                }
            } else {
                zoneId = Long.parseLong(zoneIdByZipObj.get(0).toString());
            }
            System.out.println("zoneId" + zoneId);

            BigDecimal totalWeight = new BigDecimal(0);
            for (BasketItem itm : this.getBasket().getItems()) {

                BigDecimal weight = em.createNamedQuery("GetProductKilo", BigDecimal.class)
                        .setParameter(1, itm.getProd().getId())
                        .getSingleResult();
                totalWeight = totalWeight.add(weight.multiply(new BigDecimal(itm.getQuantity())));
            }

            System.out.println("TOTAL WEIGHT" + totalWeight);
            BigDecimal cost = new BigDecimal(0);

            Double varos = totalWeight.doubleValue();

            List<ShopWeightShipRules> weightRules = this.getEm().createQuery("SELECT w FROM " +
                    " ShopWeightShipRules w " +
                    " JOIN w.shopId sh " +
                    " JOIN w.shippingClassId klass " +
                    " JOIN w.zoneId z " +
                    "  WHERE w.active = 1 AND z.id = :zoneId " +
                    "  AND klass.id = :klassId AND sh.id = :shopId")
                    .setParameter("zoneId", zoneId)
                    .setParameter("klassId", this.getBasket().getShipMethod().getId())
                    .setParameter("shopId", this.getBasket().getShop().getId())
                    .getResultList();
            for (ShopWeightShipRules wRul : weightRules) {
                Double upperValue = wRul.getOverThanKg().doubleValue();
                Double lowerValue = wRul.getLessThanKg().doubleValue();

                boolean lowerLimitOK = (varos > lowerValue) || (varos >= lowerValue && wRul.isLessEqual());
                boolean upperLimitOK = (varos < upperValue) || (varos <= upperValue && wRul.isOverEqual());

                if (lowerLimitOK && upperLimitOK) {
                    cost = cost.add(wRul.getBaseCost());
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
                    .setParameter("kg", totalWeight)
                    .setParameter("klassId", this.getBasket().getShipMethod().getId())
                    .setParameter("shopId", this.getBasket().getShop().getId())
                    .getResultList();
            if (overWeightRules.size() > 0) {
                //  Double overLimit = overWeightRules.get(0).getOverTotalWeight().doubleValue();
                //   Double extraWeight =  varos - overLimit;
                BigDecimal overLimit = totalWeight.subtract(overWeightRules.get(0).getOverTotalWeight());
                cost = cost.add(overLimit.multiply(overWeightRules.get(0).getCharge()));
            }

            // convert to selected currency ....
            c.setShipCost(cost);
            return c;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return c;
        }
    }
}
