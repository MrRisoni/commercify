package services;

import pojo.Basket;
import pojo.BasketItem;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TaxSrcv {


    private Basket basket;
    private EntityManager em;


    public TaxSrcv(){}

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
        BigDecimal totalTax = new BigDecimal(0);
        BigDecimal netPriceTotal = new BigDecimal(0);
        BigDecimal taxOfProducts = new BigDecimal(0);

        for (BasketItem itm: basket.getItems()) {
            System.out.println("DB PRICE OF is " + itm.getProd().getId());

            List<Object[]> res = this.em.createNativeQuery("SELECT category_id,price FROM Products WHERE id = :pid AND taxable = 1 LIMIT 1")
                    .setParameter("pid",itm.getProd().getId()).getResultList();
            if (res.size() >0) {
                BigDecimal productPriceDb = new BigDecimal(res.get(0)[1].toString());
                System.out.println("DB PRICE OF is " + productPriceDb);
                netPriceTotal = (new BigDecimal(itm.getQuantity())).multiply(productPriceDb);

                List<Object> taxZip= this.em.createNativeQuery("SELECT rate FROM shop_tax_zipcode_rules " +
                        "WHERE active = 1 AND zip_codes LIKE '%" + this.basket.getShipTop().getPostCode() +  "%' LIMIT 1")
                        .getResultList();

                BigDecimal productTaxRate = new BigDecimal(0);
                BigDecimal productTaxFlat = new BigDecimal(0);

                if (taxZip.size() >0)
                {

                }
                else {
                    List<Object> taxRegion= this.em.createNativeQuery("SELECT rate FROM  shop_tax_region_rules " +
                            "WHERE active = 1 AND zip_codes LIKE '%" + this.basket.getShipTop().getPostCode() +  "%' LIMIT 1")
                            .getResultList();
                    if (taxRegion.size()>0) {

                    }
                    else {
                        List<Object> taxGeneral = this.em.createNativeQuery("SELECT rate FROM  shop_tax_rules " +
                                "WHERE active = 1  LIMIT 1")
                                .getResultList();
                        if (taxGeneral.size()>0) {
                            productTaxRate = new BigDecimal(res.get(0)[0].toString());
                        }
                    }
                }
                taxOfProducts = productPriceDb.multiply(productTaxRate);

                totalTax = totalTax.add(taxOfProducts);

            }








        }

        return totalTax;
    }
}
