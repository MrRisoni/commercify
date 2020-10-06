package services;

import common.Utils;
import entity.product.Products;
import entity.shipping.ShopVolumeShipRules;
import entity.shipping.ShopWeightOverShipRules;
import entity.shipping.ShopWeightShipRules;
import pojo.Basket;
import pojo.BasketItem;
import pojo.ShippingCosts;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.*;

public class VolumeService {

    private Basket basket;
    private EntityManager em;

    public VolumeService() {
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

    public ShippingCosts getTotalShippingCosts() {
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
            System.out.println("regionId" + this.getBasket().getShipTo().getRegionId().getId());
            System.out.println("zip" + this.getBasket().getShipTo().getPostCode());
            System.out.println("shopID" + this.getBasket().getShop().getId());


            // group products by shipping class
            Set<Long> shipClassesIds = new HashSet<>();
            for (BasketItem itm : basket.getItems()) {
                shipClassesIds.add(itm.getShipClassId());
            }
            BigDecimal totalVolume;
            BigDecimal cost = new BigDecimal(0);

            Iterator<Long> itKlassId = shipClassesIds.iterator();
            while (itKlassId.hasNext()) {
                totalVolume = new BigDecimal(0);
                for (BasketItem itm : basket.getItems()) {
                    if (itm.getShipClassId() == itKlassId.next()) {
                        totalVolume = totalVolume.add(Utils.getProductVolume(itm.getProd().getId()));
                    }
                }
                System.out.println("TOTAL VOL " + totalVolume);

               /* List<ShopVolumeShipRules> volumeRules = this.getEm().createQuery("SELECT vl FROM " +
                        " ShopVolumeShipRules vl " +
                        " JOIN vl.shopId sh " +
                        " JOIN vl.shippingClassId klass " +
                        " JOIN vl.zoneId z " +
                        "  WHERE vl.active = 1 AND z.id = :zoneId " +
                        "  AND klass.id = :klassId AND sh.id = :shopId")
                        .setParameter("zoneId", zoneId)
                        .setParameter("klassId", this.getBasket().getShipMethod().getId())
                        .setParameter("shopId", this.getBasket().getShop().getId())
                        .getResultList();
                System.out.println("NORMAL VOLUMES RESULT " + volumeRules.size());
                for (ShopVolumeShipRules vRul : volumeRules) {
                    Double upperValue = vRul.getOverThanVolume().doubleValue();
                    Double lowerValue = vRul.getLessThanVolume().doubleValue();

                    System.out.println(ogkos);
                    System.out.println(lowerValue);
                    System.out.println(upperValue);


                    boolean lowerLimitOK = (ogkos > lowerValue) || (ogkos >= lowerValue && ogkos.isLessEqual());
                    boolean upperLimitOK = (ogkos < upperValue) || (ogkos <= upperValue && ogkos.isOverEqual());

                    if (vRul.isLessThanInfinity()) {
                        upperLimitOK = true;
                    }

                    System.out.println("lowerLimitOK " + lowerLimitOK);
                    System.out.println("upperLimitOK " + upperLimitOK);

                    if (lowerLimitOK && upperLimitOK) {
                        cost = cost.add(vRul.getBaseCost());
                        break;
                    }
                }*/

            }


            // convert to selected currency ....
            c.setShipCost(cost);
            return c;
        } catch (Exception ex) {
            ex.printStackTrace();
            return c;
        }
    }
}
