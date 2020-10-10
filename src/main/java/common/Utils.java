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

    public static  BigDecimal getProductVolume_m3(Long productId)
    {
        BigDecimal productVol = new BigDecimal(1);
        System.out.println("DB dimensions of product " + productId);
        Products p = entityManager.createQuery("FROM Products WHERE id = :pid", Products.class)
                    .setParameter("pid", productId)
                    .getSingleResult();

        productVol = p.getDimL();
        System.out.println("DIMS FOR PRODUCT " + productId + " " + p.getDimL() + " " + p.getDimW() + " " + p.getDimH());
        productVol = productVol.multiply(p.getDimW());
        productVol = productVol.multiply(p.getDimH());

        productVol = productVol.divide(new BigDecimal(100));
        productVol = productVol.divide(new BigDecimal(100));
        productVol = productVol.divide(new BigDecimal(100));

        return productVol;
    }

    public static BigDecimal getTotalOrderWeight(Basket bsk)
    {
        BigDecimal totalWeight = new BigDecimal(0);
        for (BasketItem itm : bsk.getItems()) {
            System.out.println("GET Weight of product " +  itm.getProd().getId());
            BigDecimal weight = entityManager.createNamedQuery("GetProductKilo", BigDecimal.class)
                    .setParameter(1, itm.getProd().getId())
                    .getSingleResult();
            totalWeight = totalWeight.add(weight.multiply(new BigDecimal(itm.getQuantity())));
        }
        return totalWeight;
    }


    public static BigDecimal getTotalNetOfOrder(Basket kalathi) {
        BigDecimal totalNet = new BigDecimal(0);


        for (BasketItem itm : kalathi.getItems()) {

            Products gefundenProduct  = entityManager.createQuery("SELECT new entity.product.Products(p.id,p.price) " +
                    " FROM Products p WHERE p.id=:productId",Products.class)
                    .setParameter("productId",itm.getProd().getId())
                    .getSingleResult();

            totalNet =totalNet.add(gefundenProduct.getPrice());
        }

        return totalNet;
    }
}
