package core;

import com.google.gson.Gson;
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

    @PostMapping(value = "/api/category",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public HashMap<String, Object> getProducts(@RequestBody Object filterCriteria) {
        try {
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
            List<String> joinSql = new ArrayList<>();
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
                joinSql.add("JOIN ( " + smallSQL + " ) AS " + filterId + " ON " + filterId + ".product_id=p.id");

                filtrCounter++;

            }



            // dynamic join....
            String productSQL = "SELECT p.id,p.title,p.kilos,p.price FROM products p ";
            String productSQLCount = "SELECT COUNT(p.id) FROM products p ";

            String finalSQL = productSQL + String.join(" ", joinSql);
            String finalSQLCount = productSQLCount + String.join(" ", joinSql);

            if (filterVals.getMinPrice() >0 && filterVals.getMaxPrice() >0) {
                finalSQL += " WHERE p.price >= " + String.valueOf(filterVals.getMinPrice());
                finalSQL += " AND p.price <= " + String.valueOf(filterVals.getMaxPrice());

                finalSQLCount += " WHERE p.price >= " + String.valueOf(filterVals.getMinPrice());
                finalSQLCount += " AND p.price <= " + String.valueOf(filterVals.getMaxPrice());

            }
            int start =  (filterVals.getCurrentPage() >1) ? (filterVals.getCurrentPage() * filterVals.getPerPage()) - filterVals.getPerPage() : 0;

            finalSQL += " ORDER BY " + orderByCol;
            finalSQL += " LIMIT " + String.valueOf(start)  + ","+ String.valueOf(filterVals.getPerPage());
            HashMap<String, Object> rsp = new HashMap<>();
            rsp.put("sql", finalSQL);


            System.out.println(finalSQL);

            Object resultsCount = em.createNativeQuery(finalSQLCount).getSingleResult();
            int totalFetchedProducts = Integer.valueOf(resultsCount.toString());

            List<Object[]> products = em.createNativeQuery(finalSQL, "JediResult").getResultList();





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
            rsp.put("resCount",totalFetchedProducts);

          //  rsp.put("products",criteriaResult);


            return rsp;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        }
    }
}
