package services;

import dto.TaxParts;
import pojo.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TaxSrvc {

    private Basket basket;
    private EntityManager em;

    public TaxSrvc() {
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

    public TaxInfo getTotalTaxData() {
        try {
            System.out.println("getTotalTaxData total products " + basket.getItems());


            TaxInfo txnf = new TaxInfo();

            BigDecimal totalTax = new BigDecimal(0);
            BigDecimal netPriceTotal = new BigDecimal(0);
            BigDecimal taxOfProductShipBased = new BigDecimal(0);
            BigDecimal taxOfProductBillBased = new BigDecimal(0);

            ProductTaxInput input = new ProductTaxInput();
            input.setBillCountry(this.getBasket().getShipTo().getCountryCode());
            input.setShipCountry(this.getBasket().getShipTo().getCountryCode());
            input.setBillZipCode(this.getBasket().getBillTo().getPostCode());
            input.setShipZipCode(this.getBasket().getShipTo().getPostCode());
            input.setBillRegion(this.getBasket().getBillTo().getRegionId());
            input.setShipRegion(this.getBasket().getShipTo().getRegionId());
            input.setUpdatedAt(this.getBasket().getUpdatedAt());

            for (BasketItem itm : basket.getItems()) {
                System.out.println("DB PRICE OF is " + itm.getProd().getId());
                ProductTax finalTax = new ProductTax();
                System.out.println("calc tax of " + itm.getProd().getId());

                List<Object[]> res = this.em.createNativeQuery("SELECT category_id,price FROM products WHERE id = :pid AND taxable = 1 LIMIT 1")
                        .setParameter("pid", itm.getProd().getId()).getResultList();
                if (res.size() > 0) {

                    BigDecimal taxForThisProduct = new BigDecimal(0);

                    BigDecimal productPriceDb = new BigDecimal(res.get(0)[1].toString());
                    System.out.println("DB PRICE OF is " + productPriceDb);
                    netPriceTotal = (new BigDecimal(itm.getQuantity())).multiply(productPriceDb);

                    input.setProductCategoryId(Long.parseLong(res.get(0)[0].toString()));

                    finalTax = this.extractTaxes(input);

                    System.out.println("SHIP TAX RATE " + finalTax.getRateShipBased());
                    System.out.println("BILL  TAX RATE " + finalTax.getRateBillBased());

                    System.out.println("SHIP TAX FLAT " + finalTax.getFlatShipBased());
                    System.out.println("BILL TAX FLAT " + finalTax.getFlatBillBased());


                    taxOfProductShipBased = netPriceTotal.multiply(finalTax.getRateShipBased()).divide(new BigDecimal(100));
                    taxOfProductShipBased = taxOfProductShipBased.add(finalTax.getFlatShipBased());

                    taxOfProductBillBased = netPriceTotal.multiply(finalTax.getRateBillBased()).divide(new BigDecimal(100));
                    taxOfProductBillBased = taxOfProductBillBased.add(finalTax.getFlatBillBased());

                    taxForThisProduct = taxForThisProduct.add(taxOfProductShipBased);
                    taxForThisProduct = taxForThisProduct.add(taxOfProductBillBased);


                    totalTax = totalTax.add(taxForThisProduct);

                    txnf.addToHashMap(itm.getProd().getId(),taxForThisProduct);
                }

            }

            txnf.setTotalTax(totalTax);
            if (totalTax.equals(null)) {
                return new TaxInfo();
            }
            else {
                txnf.setTotalTax(totalTax.setScale(2, BigDecimal.ROUND_UP));
                return txnf;
            }
        } catch (Exception ex) {
            return new TaxInfo();
        }
    }

    private ProductTax extractTaxes(ProductTaxInput inpt) {
        BigDecimal rateShipBased = new BigDecimal(0);
        BigDecimal flatShipBased = new BigDecimal(0);

        BigDecimal rateBillBased = new BigDecimal(0);
        BigDecimal flatBillBased = new BigDecimal(0);
        try {
            List<TaxParts> zipShip = this.getTaxZipCodeRulesHQL(inpt, "ship");
            List<TaxParts> zipBill = this.getTaxZipCodeRulesHQL(inpt, "bill");


            if (zipShip.size() == 0 && zipBill.size() == 0) {
                List<TaxParts> regionShip = this.getTaxRegionRulesHQL(inpt, "ship");
                List<TaxParts> regionBill = this.getTaxRegionRulesHQL(inpt, "bill");

                if (regionShip.size() == 0 && regionBill.size() == 0) {
                    List<TaxParts> taxShip = this.getTaxGeneralRulesHQL(inpt, "ship");
                    List<TaxParts> taxBill = this.getTaxGeneralRulesHQL(inpt, "bill");

                    if (taxShip.size() > 0) {
                        rateShipBased = taxShip.get(0).getRate();
                        flatShipBased = taxShip.get(0).getFlatCost();
                    }
                    if (taxBill.size() > 0) {
                        rateBillBased = taxBill.get(0).getRate();
                        flatBillBased = taxBill.get(0).getFlatCost();
                    }

                } else {
                    if (regionShip.size() > 0) {
                        rateShipBased = regionShip.get(0).getRate();
                        flatShipBased = regionShip.get(0).getFlatCost();
                    }
                    if (regionBill.size() > 0) {
                        rateBillBased = regionBill.get(0).getRate();
                        flatBillBased = regionBill.get(0).getFlatCost();
                    }
                }
            } else {

                if (zipShip.size() > 0) {
                    rateShipBased = zipShip.get(0).getRate();
                    flatShipBased = zipShip.get(0).getFlatCost();
                }
                if (zipBill.size() > 0) {
                    rateBillBased = zipBill.get(0).getRate();
                    flatBillBased = zipBill.get(0).getFlatCost();
                }
            }
            return new ProductTax(rateShipBased, flatShipBased, rateBillBased, flatBillBased);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return new ProductTax(rateShipBased, flatShipBased, rateBillBased, flatBillBased);
        }

    }

    private List<TaxParts> getTaxGeneralRulesHQL(ProductTaxInput inpt, String taxAddress) {
        String countryCode = taxAddress.equals("ship") ? inpt.getShipCountry() : inpt.getBillCountry();

        return this.em.createQuery("SELECT new dto.TaxParts(tx.flatCost,tx.rate) FROM ShopTaxRules tx " +
                " JOIN tx.productCategoryId pCat " +
                "   WHERE pCat.id = :productCatId AND " +
                " tx.taxAddress = :taxAddress AND tx.countryCode =:code AND tx.active = 1 " +
                " AND tx.activeFrom <= :today AND tx.activeUntil > :today ")
                .setParameter("code", countryCode)
                .setParameter("today", inpt.getUpdatedAt())
                .setParameter("taxAddress", taxAddress)
                .setParameter("productCatId", inpt.getProductCategoryId()).getResultList();

    }

    private List<TaxParts> getTaxRegionRulesHQL(ProductTaxInput inpt, String taxAddress) {
        String countryCode = taxAddress.equals("ship") ? inpt.getShipCountry() : inpt.getBillCountry();
        Long regionId = taxAddress.equals("ship") ? inpt.getShipRegion().getId() : inpt.getBillRegion().getId();

        return this.em.createQuery("SELECT new dto.TaxParts(txrg.flatCost,txrg.rate) FROM ShopTaxRegionRules txrg " +
                " JOIN txrg.productCategoryId pCat " +
                " JOIN txrg.regionId rg " +
                "   WHERE pCat.id = :productCatId AND rg.id = :regionId " +
                "   AND txrg.taxAddress = :taxAddress AND txrg.countryCode =:code AND txrg.active = 1 " +
                "   AND txrg.activeFrom <= :today AND txrg.activeUntil > :today ")
                .setParameter("code", countryCode)
                .setParameter("today", inpt.getUpdatedAt())
                .setParameter("regionId", regionId)
                .setParameter("taxAddress", taxAddress)
                .setParameter("productCatId", inpt.getProductCategoryId()).getResultList();

    }

    private List<TaxParts> getTaxZipCodeRulesHQL(ProductTaxInput inpt, String taxAddress) {
        String countryCode = taxAddress.equals("ship") ? inpt.getShipCountry() : inpt.getBillCountry();
        Long regionId = taxAddress.equals("ship") ? inpt.getShipRegion().getId() : inpt.getBillRegion().getId();
        String zip = taxAddress.equals("ship") ? inpt.getShipZipCode() : inpt.getBillZipCode();

        System.out.println("getTaxZipCodeRulesHQL");
        System.out.println("code " + countryCode);
        System.out.println("taxAddress " + taxAddress);
        System.out.println("today " + inpt.getUpdatedAt());
        System.out.println("regionId " + regionId);
        System.out.println("zip " + zip);
        System.out.println("productCatId " + inpt.getProductCategoryId());

        return this.em.createQuery("SELECT new dto.TaxParts(txz.flatCost,txz.rate) FROM ShopTaxZipCodeRules txz " +
                " JOIN txz.productCategoryId pCat " +
                " JOIN txz.regionId rg " +
                "  WHERE txz.zipCodes LIKE CONCAT('%',:zip,'%')" +
                "  AND pCat.id = :productCatId AND rg.id = :regionId " +
                "  AND txz.taxAddress = :taxAddress AND txz.countryCode =:code AND txz.active = 1 " +
                "   AND txz.activeFrom <= :today AND txz.activeUntil > :today ")
                .setParameter("code", countryCode)
                .setParameter("taxAddress", taxAddress)
                .setParameter("today", inpt.getUpdatedAt())
                .setParameter("regionId", regionId)
                .setParameter("zip", zip)
                .setParameter("productCatId", inpt.getProductCategoryId()).getResultList();

    }
}
