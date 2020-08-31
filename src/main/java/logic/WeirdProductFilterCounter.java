package logic;

import entity.product.ProductAttributesValues;
import entity.product.Products;
import pojo.ProductFilterPojo;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

public class WeirdProductFilterCounter {

    EntityManager em;
    private Long shopId;
    private Long categoryId;
    private CriteriaQuery critQry;
    private CriteriaBuilder builder;

    public WeirdProductFilterCounter(EntityManager em, Long shopId, Long categoryId, CriteriaQuery critQry, CriteriaBuilder builder) {
        this.em = em;
        this.shopId = shopId;
        this.categoryId = categoryId;
        this.critQry = critQry;
        this.builder = builder;
    }

    private Subquery buildSubQuery(List<ProductFilterPojo> filtra) {
        Subquery<Products> subQuery = critQry.subquery(Products.class);
        Root<Products> subRoot = subQuery.from(Products.class);
        CollectionJoin<ProductAttributesValues, Products> joinWithAttributeValues = subRoot.joinCollection("productAttributesValuesCollection", JoinType.INNER);
        Predicate[] subQryPredicates;

        ProductFilterPojo filtro;
        // if (filtra.size() == 1) {
        filtro = filtra.get(0);

        subQryPredicates = new Predicate[3];
        subQryPredicates[0] = builder.equal(subRoot.get("shopKey"), shopId);
        subQryPredicates[1] = builder.equal(subRoot.get("categoryKey"), categoryId);

        if (filtro.getType().equals("bool")) {
            subQryPredicates[2] = builder.and(builder.equal(joinWithAttributeValues.get("valueBoolean"), 1),
                    builder.equal(joinWithAttributeValues.get("attributeKey"), filtro.getAttributeId()));

        } else if (filtro.getType().equals("range")) {
            subQryPredicates[2] = builder.and(builder.between(joinWithAttributeValues.get("valueNumeric"), filtro.getFrom(), filtro.getTo()),
                    builder.equal(joinWithAttributeValues.get("attributeKey"), filtro.getAttributeId()));

        } else if (filtro.getType().equals("str")) {
            subQryPredicates[2] = builder.and(builder.equal(joinWithAttributeValues.get("valueStr"), filtro.getValueStr()),
                    builder.equal(joinWithAttributeValues.get("attributeKey"), filtro.getAttributeId()));

        } else {
            System.out.println("UNKNOWN FILTER TYPE " + filtro.getType());
        }
        System.out.println("TYPE " + filtro.getType());
        // }
       /*  else {
            // OR
           // AND it may have many values ....

            subQryPredicates = new Predicate[3];
            subQryPredicates[0] = builder.equal(subRoot.get("shopKey"), shopId);
            subQryPredicates[1] = builder.equal(subRoot.get("categoryKey"), categoryId);

            Predicate ssdJa = builder.and(builder.equal(joinWithAttributeValues.get("valueBoolean"), 1),
                    builder.equal(joinWithAttributeValues.get("attributeKey"),9));

            Predicate ssdNej = builder.and(builder.equal(joinWithAttributeValues.get("valueBoolean"), 1),
                    builder.equal(joinWithAttributeValues.get("attributeKey"),9));


            subQryPredicates[2] = builder.or(ssdJa,ssdNej);


        } */


        subQuery.select(subRoot)
                .where(subQryPredicates);
        return subQuery;

    }

}
