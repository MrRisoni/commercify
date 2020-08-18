package core;

import dto.ProductPreview;
import entity.*;
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
            CollectionJoin<ProductAttributesValues,Products> joinWithAttributeValues = rootProduct.joinCollection("productAttributesValuesCollection", JoinType.INNER);
            Join<ProductCategoryAttributes,ProductAttributesValues> joinWithAttributes = joinWithAttributeValues.join("attributeId", JoinType.INNER);

            Predicate[] predicates = new Predicate[3];
            predicates[0] = builder.ge(rootProduct.get("price"),90);
            predicates[1] = builder.le(rootProduct.get("price"),4300);
            // weight
            predicates[2] = builder.le(rootProduct.get("kilos"),15.1);
            // limit by ssd!!!

            // if set for order price
            criteriaQry.orderBy(builder.asc(rootProduct.get(Products_.price)));
           /* criteriaQry.orderBy(builder.desc(rootProduct.get(Products_.price)));

            criteriaQry.orderBy(builder.desc(rootProduct.get(Products_.totalClicks)));
            criteriaQry.orderBy(builder.desc(rootProduct.get(Products_.totalOrders)));
            criteriaQry.orderBy(builder.desc(rootProduct.get(Products_.created)));
*/
/*
            criteriaQry.multiselect(builder.construct(ProductPreview.class,
                    rootProduct.get(Products_.id),
                    rootProduct.get(Products_.title),
                    rootProduct.get(Products_.thumbnailUrl),
                    rootProduct.get(Products_.price)
            ));*/

            List<Products>criteriaResult = em.createQuery(  criteriaQry.select(rootProduct).where(predicates) ).getResultList();

            int ResultCount = criteriaResult.size();

            HashMap<String,Object> rsp =new HashMap<>();

            BigDecimal minWeight = (ResultCount >1) ? criteriaResult.get(0).getKilos() : new BigDecimal(0);
            BigDecimal maxWeight = (ResultCount >1) ? criteriaResult.get(0).getKilos() : new BigDecimal(0);
            BigDecimal minPrice = (ResultCount >1) ? criteriaResult.get(0).getPrice() : new BigDecimal(0);
            BigDecimal maxPrice = (ResultCount >1) ? criteriaResult.get(0).getPrice() : new BigDecimal(0);

            for (Products p : criteriaResult) {
                if (p.getKilos().compareTo(maxWeight)==1) {
                    maxWeight = p.getKilos();
                }
                if (p.getKilos().compareTo(minWeight)==-1) {
                    minWeight = p.getKilos();
                }

                if (p.getPrice().compareTo(maxPrice)==1) {
                    maxPrice = p.getPrice();
                }
                if (p.getPrice().compareTo(minPrice)==-1) {
                    minPrice = p.getPrice();
                }
            }

            rsp.put("minWeight",minWeight);
            rsp.put("maxWeight",maxWeight);

            rsp.put("minPrice",minPrice);
            rsp.put("maxPrice",maxPrice);

          //  rsp.put("products",criteriaResult);

            return rsp;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;

        }
    }
}
