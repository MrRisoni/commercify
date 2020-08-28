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



    private Subquery buildSubQry(CriteriaQuery criteriaQry,CriteriaBuilder builder,Long shopId,Long categoryId,ProductFilterPojo filtro)
    {
        Subquery<Products> subQuery = criteriaQry.subquery(Products.class);
        Root<Products> subRoot = subQuery.from(Products.class);
        CollectionJoin<ProductAttributesValues, Products> joinWithAttributeValues = subRoot.joinCollection("productAttributesValuesCollection", JoinType.INNER);
        Predicate[] subQryPredicates = new Predicate[3];
        subQryPredicates[0] = builder.equal(subRoot.get("shopKey"),shopId);
        subQryPredicates[1] = builder.equal(subRoot.get("categoryKey"),categoryId);

        if (filtro.getType().equals("bool")) {
            subQryPredicates[2] = builder.and(builder.equal(joinWithAttributeValues.get("valueBoolean"), 1),
                    builder.equal(joinWithAttributeValues.get("attributeKey"), filtro.getAttributeId()));
        }
        else if (filtro.getType().equals("range")) {
            subQryPredicates[2] = builder.and(builder.between(joinWithAttributeValues.get("valueNumeric"), filtro.getFrom(),filtro.getTo()),
                    builder.equal(joinWithAttributeValues.get("attributeKey"), filtro.getAttributeId()));
        }
        else {
            System.out.println("UNKNOWN FILTER TYPE " + filtro.getType());
        }

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
            if (filterVals.getMinPrice() >-1) {
                predicatesLen++;
            }
            if (filterVals.getMaxPrice() >-1) {
                predicatesLen++;
            }
            predicatesLen += filterVals.getFilters().size();
            System.out.println("TOTAL PREDICATES SIZE " + predicatesLen);

            Predicate[] ProductPredicates = new Predicate[predicatesLen];
            ProductPredicates[0] = builder.equal(rootProduct.get("shopKey"),shopId);
            ProductPredicates[1] = builder.equal(rootProduct.get("categoryKey"),categoryId);
            ProductPredicates[2] = builder.equal(rootProduct.get("active"),1);
            ProductPredicates[3] = builder.equal(rootProduct.get("visible"),1);

            ProductPredicates[4] = builder.ge(rootProduct.get("price"),filterVals.getMinPrice());
            ProductPredicates[5] = builder.le(rootProduct.get("price"),filterVals.getMaxPrice());



            int start = predicatesLen - filterVals.getFilters().size();
            int z=0;
           for (int prc = start; prc < predicatesLen; prc++)
            {
                System.out.println("PRC " + prc + " zz " + z);
              ProductPredicates[prc] = builder.in(rootProduct.get("id")).value(buildSubQry(criteriaQry,builder,shopId,categoryId,filterVals.getFilters().get(z)));
                System.out.println("PRC " + prc + " zz " + z);

                z++;
            }



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



            List<Products>criteriaResult = em.createQuery(
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
            rsp.put("totalPages",50);
            rsp.put("countByAttributeCategories",null);
            if (totalProducts >0) {
                rsp.put("firstProduct",criteriaResult.get(0).getCode());
            }
            rsp.put("ranges", ranges);

            return rsp;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private void GroupByBooleanValues()
    {

    }

    private void GroupByStringValues()
    {

    }

    private void GroupByRangeValues()
    {
        // the user will set ranges!!!
    }
 /*



    @PostMapping(value = "/api/category",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public HashMap<String, Object> getProducts(@RequestBody Object filterCriteria) {
        try {
            Long shopId = 2L;
            Long categoryId = 5L;

            EntityManager em = HibernateUtil.getEM();
            Gson g = new Gson();
            BrowsePojo filterVals = new Gson().fromJson(g.toJson(filterCriteria), BrowsePojo.class);

            System.out.println("ORDER BY ");
            System.out.println(filterVals.getOrderBy().getOrderBy());
            String orderByCol = " price ASC ";
            switch (filterVals.getOrderBy().getOrderBy()) {
                case "price":
                    orderByCol = " price " + filterVals.getOrderBy().getSortOrder();
                    break;
                case "best_seller": // best seller
                    orderByCol = " total_orders DESC";
                    break;
                case "popularity":
                    orderByCol = " total_views DESC";
                    break;
                case "avg_rating":
                    orderByCol = " avg_rating DESC";
                    break;
                case "new_products":
                    orderByCol = " created DESC";
                    break;
            }

            // per page!!!


            // for every filter make a query
            List<String> joinSqlFiltering = new ArrayList<>();
            List<String> joinSqlAttributeFiltering = new ArrayList<>();

            int filtrCounter = 0;
            String filterId = "";
            String smallSQL = "";
            for (ProductFilterPojo filterPojo : filterVals.getFilters()) {
                System.out.println("-----------------");
                System.out.println(filterPojo.getAttributeId());
                System.out.println(filterPojo.getValue());
                System.out.println(filterPojo.getTo());

                // sql injection!!!
                smallSQL = "SELECT product_id FROM product_attributes_values WHERE attribute_id=" + filterPojo.getAttributeId();
                if (filterPojo.getType().equals("range")) {
                    smallSQL += " AND value_numeric >= " + String.valueOf(filterPojo.getFrom()) + " AND value_numeric <=" + String.valueOf(filterPojo.getTo());
                }
                if (filterPojo.getType().equals("number")) {
                    smallSQL += " AND value_numeric = " + String.valueOf(filterPojo.getValue());
                }
                filterId = "filtr" + String.valueOf(filtrCounter);
                joinSqlFiltering.add("JOIN ( " + smallSQL + " ) AS " + filterId + " ON " + filterId + ".product_id=p.id");
                joinSqlAttributeFiltering.add("JOIN ( " + smallSQL + " ) AS " + filterId + " ON " + filterId + ".product_id=pav.product_id ");

                filtrCounter++;

            }
            // for every RANGEable attribute get max and min value

            String minMaxAttributeSQL = " SELECT pav.attribute_id,pca.code, MIN(pav.value_numeric) ,MAX(pav.value_numeric)  " +
                    "  FROM product_attributes_values pav " +
                    "  JOIN products p " +
                    "  JOIN  product_category_attributes pca ON pca.id = pav.attribute_id " +
                    "  WHERE pca.rangeable =1 AND p.shop_id = " + String.valueOf(shopId) +
                    " AND p.category_id = " + String.valueOf(categoryId) + " GROUP BY pav.attribute_id ";

            // GET the count
            String binaryAttributeSQL = " ";

SELECT pav.attribute_id,pca.title, pav.valueNumeric,COUNT(pav.id) FROM product_attributes_values pav JOIN product_category_attributes pca ON pca.id = pav.attribute_id WHERE pca.isBoolean =1 GROUP BY pav.attribute_id,pav.valueNumeric


            // dynamic join....
            String productSQL = "SELECT p.id,p.title,p.kilos,p.price FROM products p ";
            String productSQLCount = "SELECT COUNT(p.id) FROM products p ";

            String finalSQL = productSQL + String.join(" ", joinSqlFiltering);
            String finalSQLCount = productSQLCount + String.join(" ", joinSqlFiltering);


            finalSQL += " WHERE p.shop_id = " + String.valueOf(shopId);
            finalSQL += " AND p.category_id = " + String.valueOf(categoryId);

            finalSQLCount += " WHERE p.shop_id = " + String.valueOf(shopId);
            finalSQLCount += " AND p.category_id = " + String.valueOf(categoryId);

            if (filterVals.getMinPrice() > 0 && filterVals.getMaxPrice() > 0) {
                finalSQL += " AND p.price >= " + String.valueOf(filterVals.getMinPrice());
                finalSQL += " AND p.price <= " + String.valueOf(filterVals.getMaxPrice());

                finalSQLCount += " AND p.price >= " + String.valueOf(filterVals.getMinPrice());
                finalSQLCount += " AND p.price <= " + String.valueOf(filterVals.getMaxPrice());


                minMaxAttributeSQL = " SELECT pav.attribute_id,pca.code, MIN(pav.value_numeric) ,MAX(pav.value_numeric)  " +
                        "   FROM product_attributes_values pav " +
                        " JOIN products p " +
                        "  JOIN  product_category_attributes pca ON pca.id = pav.attribute_id " +
                        "  WHERE pca.rangeable =1 " +
                        " AND p.shop_id = " + String.valueOf(shopId) +
                        " AND p.category_id = " + String.valueOf(categoryId) +
                        " AND  p.price >= " + String.valueOf(filterVals.getMinPrice()) +
                        " AND p.price <= " + String.valueOf(filterVals.getMaxPrice()) +
                        " GROUP BY pav.attribute_id ";


            }
            int start = (filterVals.getCurrentPage() > 1) ? (filterVals.getCurrentPage() * filterVals.getPerPage()) - filterVals.getPerPage() : 0;

            finalSQL += " ORDER BY " + orderByCol;
            finalSQL += " LIMIT " + String.valueOf(start) + "," + String.valueOf(filterVals.getPerPage());
            HashMap<String, Object> rsp = new HashMap<>();
            rsp.put("sql", finalSQL);


            System.out.println(finalSQL);

            Object resultsCount = em.createNativeQuery(finalSQLCount).getSingleResult();
            int totalFetchedProducts = Integer.valueOf(resultsCount.toString());

            List<Object[]> products = em.createNativeQuery(finalSQL).getResultList();

            // for every RANGEable attribute get max and min value
            HashMap<String, RangeValues> ranges = new HashMap<>();
            List<Object[]> rangesResults = em.createNativeQuery(minMaxAttributeSQL).getResultList();
            for (Object[] objRange : rangesResults) {
                ranges.put(objRange[1].toString(), new RangeValues(Double.parseDouble(objRange[2].toString()), Double.parseDouble(objRange[3].toString())));
            }








            BigDecimal minWeight = (ResultCount >0) ? criteriaResult.get(0).getKilos() : new BigDecimal(0);
            BigDecimal maxWeight = (ResultCount >0) ? criteriaResult.get(0).getKilos() : new BigDecimal(0);
            BigDecimal minPrice = (ResultCount >0) ? criteriaResult.get(0).getPrice() : new BigDecimal(0);
            BigDecimal maxPrice = (ResultCount >0) ? criteriaResult.get(0).getPrice() : new BigDecimal(0);

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


            rsp.put("resCount", totalFetchedProducts);
            rsp.put("ranges", ranges);

            //  rsp.put("products",criteriaResult);


            return rsp;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
    }

  */
}
