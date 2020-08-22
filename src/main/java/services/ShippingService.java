package services;

import entity.BillingAddress;
import entity.Products;
import entity.ShippingAddress;
import pojo.Basket;

import javax.persistence.EntityManager;
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

    public static BigDecimal getTotalShippingCost()
    {
        // query table  shipping_region_zips to get region and then ZONE
        // if result is empty query  shipping_zones_regions to get the ZONE
        // finally get shipping rule from shipping_zones table
        return null;
    }
}
