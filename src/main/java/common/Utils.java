package common;

import entity.product.Products;
import pojo.Basket;
import pojo.BasketItem;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Utils {

    private static EntityManager entityManager;


    public static void setEm(EntityManager em) {
        entityManager = em;
    }

    public static BigDecimal getProductWeight(Long productId) {
        try {
            Products prw = entityManager.createQuery("SELECT new entity.product.Products(kilos) FROM Products p WHERE p.id = :prid", Products.class)
                    .setParameter("prid", productId)
                    .getSingleResult();

            return prw.getKilos();
        } catch (Exception ex) {
            return new BigDecimal(0);
        }
    }

    public static BigDecimal getProductVolume_m3(Long productId) {
        BigDecimal productVol = new BigDecimal(1);
        System.out.println("DB dimensions of product " + productId);
        Products p = entityManager.createQuery("SELECT new entity.product.Products(dimL,dimW,dimH) FROM Products p WHERE p.id = :prid", Products.class)
                .setParameter("prid", productId)
                .getSingleResult();

        productVol = p.getDimL();
        System.out.println("DIMS FOR PRODUCT " + productId + " " + p.getDimL() + " " + p.getDimW() + " " + p.getDimH());
        productVol = productVol.multiply(p.getDimW());
        productVol = productVol.multiply(p.getDimH());

        productVol = productVol.divide(new BigDecimal(100));
        productVol = productVol.divide(new BigDecimal(100));
        productVol = productVol.divide(new BigDecimal(100));
        System.out.println("vol is  " + productVol);

        return productVol;
    }

    public static BigDecimal getProductVolume_cm3(Long productId) {
        BigDecimal productVol = new BigDecimal(1);
        System.out.println("DB dimensions of product " + productId);
        Products p = entityManager.createQuery("SELECT new entity.product.Products(dimL,dimW,dimH) FROM Products p WHERE p.id = :prid", Products.class)
                .setParameter("prid", productId)
                .getSingleResult();

        productVol = p.getDimL();
        System.out.println("DIMS FOR PRODUCT " + productId + " " + p.getDimL() + " " + p.getDimW() + " " + p.getDimH());
        productVol = productVol.multiply(p.getDimW());
        productVol = productVol.multiply(p.getDimH());


        System.out.println("vol is  " + productVol);

        return productVol;
    }

    public static BigDecimal getTotalOrderWeight(Basket bsk) {
        BigDecimal totalWeight = new BigDecimal(0);
        try {
            System.out.println("getTotalOrderWeight Func bsk size = " + bsk.getItems().size());
            for (BasketItem itm : bsk.getItems()) {
                System.out.println("GET Weight of product " + itm.getProd().getId());

                Products prw = entityManager.createQuery("SELECT new entity.product.Products(kilos) FROM Products p WHERE p.id = :prid", Products.class)
                        .setParameter("prid", itm.getProd().getId())
                        .getSingleResult();

                System.out.println("getTotalOrderWeight kilo of product " + itm.getProd().getId() + " = " + prw.getKilos());

                totalWeight = totalWeight.add(prw.getKilos().multiply(new BigDecimal(itm.getQuantity())));
            }
            return totalWeight;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new BigDecimal(100);
        }
    }


    public static BigDecimal getTotalNetOfOrder(Basket kalathi) {
        BigDecimal totalNet = new BigDecimal(0);


        for (BasketItem itm : kalathi.getItems()) {

            Products gefundenProduct = entityManager.createQuery("SELECT new entity.product.Products(p.id,p.price) " +
                    " FROM Products p WHERE p.id=:productId", Products.class)
                    .setParameter("productId", itm.getProd().getId())
                    .getSingleResult();

            totalNet = totalNet.add(gefundenProduct.getPrice());
        }

        return totalNet;
    }
}
