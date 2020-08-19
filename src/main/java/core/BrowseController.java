package core;

import com.google.gson.Gson;
import dto.RangeValues;
import entity.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pojo.BrowsePojo;
import pojo.ProductFilterPojo;

import javax.persistence.EntityManager;
import java.util.*;

@RestController
@CrossOrigin
public class BrowseController {


 /*   @PostMapping(value = "/api/search",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public HashMap<String, Object> searchProducts(@RequestBody Object filterCriteria) {

    }*/


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
                    smallSQL += " AND valueNumeric >= " + String.valueOf(filterPojo.getFrom()) + " AND valueNumeric <=" + String.valueOf(filterPojo.getTo());
                }
                if (filterPojo.getType().equals("number")) {
                    smallSQL += " AND valueNumeric = " + String.valueOf(filterPojo.getValue());
                }
                filterId = "filtr" + String.valueOf(filtrCounter);
                joinSqlFiltering.add("JOIN ( " + smallSQL + " ) AS " + filterId + " ON " + filterId + ".product_id=p.id");
                joinSqlAttributeFiltering.add("JOIN ( " + smallSQL + " ) AS " + filterId + " ON " + filterId + ".product_id=pav.product_id ");

                filtrCounter++;

            }
            // for every RANGEable attribute get max and min value

            String minMaxAttributeSQL = " SELECT pav.attribute_id,pca.title, MIN(pav.valueNumeric) ,MAX(pav.valueNumeric)  " +
                    "  FROM product_attributes_values pav " +
                    "  JOIN products p " +
                    "  JOIN  product_category_attributes pca ON pca.id = pav.attribute_id " +
                    "  WHERE pca.rangeable =1 AND p.shop_id = " + String.valueOf(shopId) +
                    " AND p.category_id = " + String.valueOf(categoryId) + " GROUP BY pav.attribute_id ";

            // GET the count
            String binaryAttributeSQL = " ";
/*
SELECT pav.attribute_id,pca.title, pav.valueNumeric,COUNT(pav.id) FROM product_attributes_values pav JOIN product_category_attributes pca ON pca.id = pav.attribute_id WHERE pca.isBoolean =1 GROUP BY pav.attribute_id,pav.valueNumeric
 */

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



                minMaxAttributeSQL = " SELECT pav.attribute_id,pca.title, MIN(pav.valueNumeric) ,MAX(pav.valueNumeric)  " +
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
                ranges.put(objRange[1].toString(),new RangeValues(Double.parseDouble(objRange[2].toString()),Double.parseDouble(objRange[3].toString())));
            }






/*

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

            */
            rsp.put("resCount", totalFetchedProducts);
            rsp.put("ranges", ranges);

            //  rsp.put("products",criteriaResult);


            return rsp;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
    }
}
