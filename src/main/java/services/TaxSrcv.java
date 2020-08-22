package services;

import entity.ShopTaxRules;
import pojo.Basket;
import pojo.BasketItem;
import pojo.ProductTax;

import javax.persistence.EntityManager;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
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
        try {
        /*
             shop_tax_code_names contains code names for the admin
             shop_tax_rules contains rules for countries  (aka countryCodeRule)
             shop_tax_region_rules“exceptions contains exceptions to the countryCodeRule
         */
            BigDecimal totalTax = new BigDecimal(0);
            BigDecimal netPriceTotal = new BigDecimal(0);
            BigDecimal taxOfProducts = new BigDecimal(0);
            BigDecimal taxOfProductsBillBased = new BigDecimal(0);

            String shipCountry = this.getBasket().getShipTo().getCountryCode();
            String billCountry = this.getBasket().getShipTo().getCountryCode();


            for (BasketItem itm : basket.getItems()) {
                System.out.println("DB PRICE OF is " + itm.getProd().getId());
                ProductTax finalTax = new ProductTax();

                List<Object[]> res = this.em.createNativeQuery("SELECT category_id,price FROM Products WHERE id = :pid AND taxable = 1 LIMIT 1")
                        .setParameter("pid", itm.getProd().getId()).getResultList();
                if (res.size() > 0) {
                    Long prodCategoryId = Long.parseLong(res.get(0)[0].toString());
                    BigDecimal productPriceDb = new BigDecimal(res.get(0)[1].toString());
                    System.out.println("DB PRICE OF is " + productPriceDb);
                    netPriceTotal = (new BigDecimal(itm.getQuantity())).multiply(productPriceDb);

                    List<Object> taxZip = this.em.createNativeQuery("SELECT rate FROM shop_tax_zipcode_rules " +
                            "WHERE active = 1 AND zip_codes LIKE '%" + shipCountry + "%' LIMIT 1")
                            .getResultList();

                    if (taxZip.size() > 0) {

                    } else {
                        List<Object> taxRegion = this.em.createNativeQuery("SELECT rate FROM  shop_tax_region_rules " +
                                "WHERE active = 1  LIMIT 1")
                                .getResultList();
                        if (taxRegion.size() > 0) {

                        } else {
                            ProductTax generalTax = this.getGeneralTaxes(prodCategoryId, shipCountry);
                            finalTax = generalTax;
                        }
                    }// end rules calculation

                    System.out.println("TAX RATE " + finalTax.getRateShipBased());
                    taxOfProducts = netPriceTotal.multiply(finalTax.getRateShipBased()).divide(new BigDecimal(100));
                    taxOfProducts = taxOfProducts.add(finalTax.getFlatShipBased());

                    taxOfProductsBillBased = netPriceTotal.multiply(finalTax.getRateBillBased()).divide(new BigDecimal(100));
                    taxOfProductsBillBased = taxOfProductsBillBased.add(finalTax.getFlatBillBased());

                    totalTax = totalTax.add(taxOfProducts);
                    totalTax = totalTax.add(taxOfProductsBillBased);

                }


            }

            Method setNameMethod = this.getClass().getMethod("dffd", String.class);


            return totalTax.setScale(2, BigDecimal.ROUND_UP);
        }
        catch (Exception ex)
        {
            return new BigDecimal(0);
        }
    }

    private ProductTax getGeneralTaxes(Long prodCategoryId, String countryCode)
    {
        BigDecimal rateShipBased = new BigDecimal(0);
        BigDecimal flatShipBased = new BigDecimal(0);

        BigDecimal rateBillBased = new BigDecimal(0);
        BigDecimal flatBillBased = new BigDecimal(0);

        List<ShopTaxRules> taxGeneralShip = this.getGeneralRulesHQL(prodCategoryId,countryCode,"ship");
        if (taxGeneralShip.size()>0) {
            rateShipBased = taxGeneralShip.get(0).getRate();
            flatShipBased =  taxGeneralShip.get(0).getFlatCost();
        }
        List<ShopTaxRules> taxGeneralBill = this.getGeneralRulesHQL(prodCategoryId,countryCode,"bill");
        if (taxGeneralBill.size()>0) {
            rateBillBased = taxGeneralBill.get(0).getRate();
            flatBillBased =  taxGeneralBill.get(0).getFlatCost();
        }

        return  new ProductTax(rateShipBased, flatShipBased, rateBillBased, flatBillBased) ;
    }


    private List<ShopTaxRules> getGeneralRulesHQL(Long pcatId,String countryCode,String taxAddress)
    {
        return  this.em.createQuery("SELECT tx FROM ShopTaxRules tx " +
                " JOIN tx.productCategoryId pCat " +
                "   WHERE pCat.id = :productCatId AND tx.taxAddress = :taxAddress AND tx.countryCode =:code AND tx.active = 1 ")
                .setParameter("code",countryCode)
                .setParameter("taxAddress",taxAddress)
                .setParameter("productCatId",pcatId).getResultList();

    }
}
