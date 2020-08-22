package services;

import pojo.Basket;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class TaxSrcv {


    private Basket basket;
    private EntityManager em;


    public TaxSrcv(Basket basket) {
        this.basket = basket;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public BigDecimal getTotalTax()
    {
        /*
             shop_tax_code_names containts code names for the admin
             shop_tax_rules contains rules for countries  (aka countryCodeRule)
             shop_tax_region_rules“exceptions containts exceptions to the countryCodeRule
         */
        return null;
    }
}
