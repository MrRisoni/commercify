package services;

import entity.ShopTaxRegionRules;
import entity.ShopTaxRules;
import entity.ShopTaxZipCodeRules;
import pojo.Basket;
import pojo.BasketItem;
import pojo.ProductTax;
import pojo.ProductTaxInput;

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

            ProductTaxInput input= new ProductTaxInput();
            input.setBillCountry(this.getBasket().getShipTo().getCountryCode());
            input.setShipCountry(this.getBasket().getShipTo().getCountryCode());
            input.setBillZipCode(this.getBasket().getBillTo().getPostCode());
            input.setShipZipCode(this.getBasket().getShipTo().getPostCode());

            for (BasketItem itm : basket.getItems()) {
                System.out.println("DB PRICE OF is " + itm.getProd().getId());
                ProductTax finalTax = new ProductTax();

                List<Object[]> res = this.em.createNativeQuery("SELECT category_id,price FROM Products WHERE id = :pid AND taxable = 1 LIMIT 1")
                        .setParameter("pid", itm.getProd().getId()).getResultList();
                if (res.size() > 0) {

                    BigDecimal productPriceDb = new BigDecimal(res.get(0)[1].toString());
                    System.out.println("DB PRICE OF is " + productPriceDb);
                    netPriceTotal = (new BigDecimal(itm.getQuantity())).multiply(productPriceDb);

                    input.setProductCategoryId(Long.parseLong(res.get(0)[0].toString()));

                    finalTax = this.extractTaxes(input);

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
            setNameMethod.invoke(this, "Mishka"); // pass arg


            return totalTax.setScale(2, BigDecimal.ROUND_UP);
        }
        catch (Exception ex)
        {
            return new BigDecimal(-100);
        }
    }

    private ProductTax extractTaxes(ProductTaxInput inpt)
    {
        BigDecimal rateShipBased = new BigDecimal(0);
        BigDecimal flatShipBased = new BigDecimal(0);

        BigDecimal rateBillBased = new BigDecimal(0);
        BigDecimal flatBillBased = new BigDecimal(0);

        List<ShopTaxZipCodeRules> zipShip = this.getTaxZipCodeRulesHQL(inpt,"ship");
        //List<ShopTaxZipCodeRules> zipBill = this.getTaxZipCodeRulesHQL(inpt,"bill");

        if (zipShip.size() ==0) {
            List<ShopTaxRegionRules> regionShip = this.getTaxRegionRulesHQL(inpt,"ship");
            if (regionShip.size() ==0) {
                List<ShopTaxRules> taxShip = this.getTaxGeneralRulesHQL(inpt,"ship");

            }
        }
        List<ShopTaxRegionRules> regionShip = this.getTaxRegionRulesHQL(inpt,"ship");
       // List<ShopTaxRegionRules> regionBill = this.getTaxRegionRulesHQL(inpt,"bill");

        List<ShopTaxRules> taxShip = this.getTaxGeneralRulesHQL(inpt,"ship");
        List<ShopTaxRules> taxBill = this.getTaxGeneralRulesHQL(inpt,"bill");

        if (taxGeneralShip.size()>0) {
            rateShipBased = taxGeneralShip.get(0).getRate();
            flatShipBased =  taxGeneralShip.get(0).getFlatCost();
        }
        if (taxGeneralBill.size()>0) {
            rateBillBased = taxGeneralBill.get(0).getRate();
            flatBillBased =  taxGeneralBill.get(0).getFlatCost();
        }

        return  new ProductTax(rateShipBased, flatShipBased, rateBillBased, flatBillBased) ;
    }


    private List<ShopTaxRules> getTaxGeneralRulesHQL(ProductTaxInput inpt,String taxAddress)
    {
        String countryCode = taxAddress.equals("ship") ? inpt.getShipCountry() : inpt.setBillCountry();

        return  this.em.createQuery("SELECT tx FROM ShopTaxRules tx " +
                " JOIN tx.productCategoryId pCat " +
                "   WHERE pCat.id = :productCatId AND tx.taxAddress = :taxAddress AND tx.countryCode =:code AND tx.active = 1 ")
                .setParameter("code",countryCode)
                .setParameter("taxAddress",taxAddress)
                .setParameter("productCatId", inpt.getProductCategoryId()).getResultList();

    }

    private List<ShopTaxRegionRules> getTaxRegionRulesHQL(ProductTaxInput inpt,String taxAddress)
    {
        String countryCode = taxAddress.equals("ship") ? inpt.getShipCountry() : inpt.setBillCountry();

        return  this.em.createQuery("SELECT tx FROM ShopTaxRules tx " +
                " JOIN tx.productCategoryId pCat " +
                "   WHERE pCat.id = :productCatId AND tx.taxAddress = :taxAddress AND tx.countryCode =:code AND tx.active = 1 ")
                .setParameter("code",countryCode)
                .setParameter("taxAddress",taxAddress)
                .setParameter("productCatId",inpt.getProductCategoryId()).getResultList();

    }

    private List<ShopTaxZipCodeRules> getTaxZipCodeRulesHQL(ProductTaxInput inpt,String taxAddress)
    {
        String countryCode = taxAddress.equals("ship") ? inpt.getShipCountry() : inpt.setBillCountry();

        return  this.em.createQuery("SELECT tx FROM ShopTaxRules tx " +
                " JOIN tx.productCategoryId pCat " +
                "   WHERE pCat.id = :productCatId AND tx.taxAddress = :taxAddress AND tx.countryCode =:code AND tx.active = 1 ")
                .setParameter("code",countryCode)
                .setParameter("taxAddress",taxAddress)
                .setParameter("productCatId",inpt.getProductCategoryId()).getResultList();

    }
}
