package core;

import entity.HibernateUtil;
import entity.ProductAttributesValues;
import entity.Products;
import entity.Products_;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class BrowseController {


    @RequestMapping(value=  "/api/category/{categoryId}" , method = RequestMethod.GET)
    public HashMap<String,Object> getProducts(@PathVariable Long categoryId)
    {
        try {
            EntityManager em = HibernateUtil.getEM();
            // order by total orders -> best seller
            // order by avg rating
            // order by price asc
            // order by price desc
            // order by created -> new
            // order by total views -> popularity

            // per page!!!


            // get filters and all values,ranges (min-max)


            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Products> criteriaQry = builder.createQuery(Products.class);
            Root<Products> rootProduct = criteriaQry.from(Products.class);
            CollectionJoin<ProductAttributesValues,Products> joinWithAttributes = rootProduct.joinCollection("productAttributesValuesCollection", JoinType.INNER);

            Predicate[] predicates = new Predicate[2];
            predicates[0] = builder.ge(rootProduct.get("price"),90);
            predicates[1] = builder.le(rootProduct.get("price"),1300);


            // if set for order price
            criteriaQry.orderBy(builder.asc(rootProduct.get(Products_.price)));

            List<Products>criteriaResult = em.createQuery( criteriaQry.select(rootProduct).where(predicates) ).getResultList();
            int ResultCount = criteriaResult.size();

            HashMap<String,Object> rsp =new HashMap<>();
            rsp.put("min",criteriaResult.get(0).getPrice());
            rsp.put("max",criteriaResult .get(ResultCount-1).getPrice());

            BigDecimal minWeight = criteriaResult.get(0).getKilos();
            BigDecimal maxWeight = criteriaResult.get(0).getKilos();

            for (Products p : criteriaResult) {
                if (p.getKilos().compareTo(maxWeight)==1) {
                    maxWeight = p.getKilos();
                }
                if (p.getKilos().compareTo(minWeight)==-1) {
                    minWeight = p.getKilos();
                }
            }

            rsp.put("minWeight",minWeight);
            rsp.put("maxWeight",maxWeight);

            return rsp;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;

        }
    }
}
