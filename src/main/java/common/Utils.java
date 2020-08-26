package common;

import pojo.Basket;
import pojo.BasketItem;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Utils {

    public static BigDecimal getTotalOrderWeight(EntityManager em, Basket bsk)
    {
        BigDecimal totalWeight = new BigDecimal(0);
        for (BasketItem itm : bsk.getItems()) {

            BigDecimal weight = em.createNamedQuery("GetProductKilo", BigDecimal.class)
                    .setParameter(1, itm.getProd().getId())
                    .getSingleResult();
            totalWeight = totalWeight.add(weight.multiply(new BigDecimal(itm.getQuantity())));
        }
        return totalWeight;
    }
}
