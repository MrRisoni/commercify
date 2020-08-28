package core;

import com.google.gson.Gson;
import dto.RangeValues;
import entity.*;
import entity.product.ProductAttributesValues;
import entity.product.ProductCategoryAttributes;
import entity.product.Products;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pojo.BrowsePojo;
import pojo.ProductFilterPojo;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.*;

@RestController
public class BrowseController {

    public List<String> joinSqlAttributeFiltering = new ArrayList<>();
    public int filtrCounter = 0;


    private Subquery buildSubQry(CriteriaQuery criteriaQry, CriteriaBuilder builder, Long shopId, Long categoryId, ProductFilterPojo filtro) {
        Subquery<Products> subQuery = criteriaQry.subquery(Products.class);
        Root<Products> subRoot = subQuery.from(Products.class);
        CollectionJoin<ProductAttributesValues, Products> joinWithAttributeValues = subRoot.joinCollection("productAttributesValuesCollection", JoinType.INNER);
        Predicate[] subQryPredicates = new Predicate[3];
        subQryPredicates[0] = builder.equal(subRoot.get("shopKey"), shopId);
        subQryPredicates[1] = builder.equal(subRoot.get("categoryKey"), categoryId);

        String smallSQL = "SELECT product_id FROM product_attributes_values WHERE attribute_id=" + filtro.getAttributeId();

        if (filtro.getType().equals("bool")) {
            subQryPredicates[2] = builder.and(builder.equal(joinWithAttributeValues.get("valueBoolean"), 1),
                    builder.equal(joinWithAttributeValues.get("attributeKey"), filtro.getAttributeId()));

            smallSQL += " AND value_boolean = 1";
        } else if (filtro.getType().equals("range")) {
            subQryPredicates[2] = builder.and(builder.between(joinWithAttributeValues.get("valueNumeric"), filtro.getFrom(), filtro.getTo()),
                    builder.equal(joinWithAttributeValues.get("attributeKey"), filtro.getAttributeId()));

            smallSQL += " AND value_numeric >= " + String.valueOf(filtro.getFrom()) + " AND value_numeric <=" + String.valueOf(filtro.getTo());

        } else {
            System.out.println("UNKNOWN FILTER TYPE " + filtro.getType());
        }

        String filterId = "filtr" + String.valueOf(this.filtrCounter);
        this.joinSqlAttributeFiltering.add("JOIN ( " + smallSQL + " ) AS " + filterId + " ON " + filterId + ".product_id = p.id ");

        System.out.println(smallSQL);
        this.filtrCounter++;


        System.out.println("TYPE " + filtro.getType());
        subQuery.select(subRoot)
                .where(subQryPredicates);
        return subQuery;

    }

    @PostMapping(value = "/api/category/criteria",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )

    public HashMap<String, Object> getProductsWithCriteriaBuilder(@RequestBody Object filterCriteria) {
        try {
            Long shopId = 2L;
            Long categoryId = 5L;

            this.joinSqlAttributeFiltering.clear();
            this.filtrCounter = 0;


            EntityManager em = HibernateUtil.getEM();
            Gson g = new Gson();
            BrowsePojo filterVals = new Gson().fromJson(g.toJson(filterCriteria), BrowsePojo.class);
            HashMap<String, RangeValues> ranges = new HashMap<>();
            HashMap<String, Object> rsp = new HashMap<>();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Products> criteriaQry = builder.createQuery(Products.class);
            Root<Products> rootProduct = criteriaQry.from(Products.class);

            // do not select all columns!!!
            int predicatesLen = 4; // category and shop, active, visible
            if (filterVals.getMinPrice() > -1) {
                predicatesLen++;
            }
            if (filterVals.getMaxPrice() > -1) {
                predicatesLen++;
            }
            predicatesLen += filterVals.getFilters().size();
            System.out.println("TOTAL PREDICATES SIZE " + predicatesLen);

            Predicate[] ProductPredicates = new Predicate[predicatesLen];
            ProductPredicates[0] = builder.equal(rootProduct.get("shopKey"), shopId);
            ProductPredicates[1] = builder.equal(rootProduct.get("categoryKey"), categoryId);
            ProductPredicates[2] = builder.equal(rootProduct.get("active"), 1);
            ProductPredicates[3] = builder.equal(rootProduct.get("visible"), 1);

            ProductPredicates[4] = builder.ge(rootProduct.get("price"), filterVals.getMinPrice());
            ProductPredicates[5] = builder.le(rootProduct.get("price"), filterVals.getMaxPrice());


            int start = predicatesLen - filterVals.getFilters().size();
            int z = 0;
            for (int prc = start; prc < predicatesLen; prc++) {
                System.out.println("PRC " + prc + " zz " + z);
                ProductPredicates[prc] = builder.in(rootProduct.get("id")).value(buildSubQry(criteriaQry, builder, shopId, categoryId, filterVals.getFilters().get(z)));
                System.out.println("PRC " + prc + " zz " + z);

                z++;
            }


            String finalSQL = " SELECT p.id FROM products p ";
            finalSQL += String.join(" ", this.joinSqlAttributeFiltering);
            finalSQL +="  WHERE p.shop_id = " + String.valueOf(shopId);
            finalSQL += " AND p.category_id = " + String.valueOf(categoryId);
            finalSQL += " AND p.visible = 1 AND p.active =1 ";
            finalSQL += " AND p.price >=0 AND p.price <=1500 ";


            switch (filterVals.getOrderBy().getOrderBy()) {
                case "price":
                    criteriaQry.orderBy(builder.asc(rootProduct.get("price")));
                    break;
                case "best_seller": // best seller
                    criteriaQry.orderBy(builder.desc(rootProduct.get("totalOrders")));
                    break;
                case "popularity":
                    criteriaQry.orderBy(builder.desc(rootProduct.get("total_clicks")));
                    break;
                case "avg_rating":
                    criteriaQry.orderBy(builder.desc(rootProduct.get("avgRating")));
                    break;
                case "new_products":
                    criteriaQry.orderBy(builder.desc(rootProduct.get("created")));
                    break;
                default:
                    criteriaQry.orderBy(builder.asc(rootProduct.get("price")));
                    break;
            }


            List<Products> criteriaResult = em.createQuery(
                    criteriaQry.multiselect(rootProduct.get("id"),
                            rootProduct.get("code"),
                            rootProduct.get("title"),
                            rootProduct.get("thumbnailUrl"),
                            rootProduct.get("price"),
                            rootProduct.get("avgRating"))
                            .where(ProductPredicates)
            ).getResultList();

            int totalProducts = criteriaResult.size();
            rsp.put("resCount", totalProducts);
            rsp.put("totalPages", 50);
            rsp.put("countByAttributeCategories", null);
            if (totalProducts > 0) {
                rsp.put("firstProduct", criteriaResult.get(0).getCode());
            }
            rsp.put("ranges", ranges);
            rsp.put("finalSQL", finalSQL);
            System.out.println(finalSQL);
            return rsp;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private void GroupByBooleanValues() {
        //use native
    }

    private void GroupByStringValues() {
        // use qry Builder!!!
    }

    private void GroupByRangeValues() {
        // the user will set ranges!!!
    }

}
