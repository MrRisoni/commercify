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
    public HashMap<String,Object> getProducts(@RequestBody Object filterCriteria )
    {
        try {
            EntityManager em = HibernateUtil.getEM();
            Gson g = new Gson();
            BrowsePojo filterVals = new Gson().fromJson(g.toJson(filterCriteria), BrowsePojo.class);

            System.out.println("ORDER BY ");
            System.out.println(filterVals.getOrderBy().getOrderBy());
            String orderByCol = " price ASC ";
            switch (filterVals.getOrderBy().getOrderBy()){
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
                case "new_productss":
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
                smallSQL= "SELECT product_id FROM product_attributes_values WHERE valueNumeric=" + String.valueOf(filterPojo.getValue()) +"  and attribute_id=" + filterPojo.getAttributeId();
                filterId = "filtr" + String.valueOf(filtrCounter);
                joinSql.add("JOIN ( " + smallSQL + " ) AS " + filterId + " ON "+filterId+".product_id=p.id");

                filtrCounter++;

            }

           /* String sqlSSD= "SELECT product_id FROM product_attributes_values WHERE valueNumeric=1.0  and attribute_id=9";

            String sqlInches= " SELECT product_id FROM product_attributes_values WHERE valueNumeric<13  and attribute_id=7";
            int filtrCounter = 0;

            List<String> joinSql = new ArrayList<>();
            String filterId = "filtr" + String.valueOf(filtrCounter);
            joinSql.add("JOIN ( " + sqlSSD + " ) AS " + filterId + " ON "+filterId+".product_id=p.id");

            filtrCounter++;
            filterId = "filtr" + String.valueOf(filtrCounter);
            joinSql.add("JOIN ( " + sqlInches + " ) AS " + filterId+ " ON "+filterId+".product_id=p.id");
*/
            // dynamic join....
            String productSQL= "SELECT p.id FROM products p ";
            String finalSQL = productSQL + String.join(" ",joinSql);
            finalSQL += " ORDER BY " + orderByCol;

            HashMap<String, Object> rsp =new HashMap<>();
            rsp.put("sql",finalSQL);


            System.out.println(finalSQL);

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
            rsp.put("resCount",ResultCount);

          //  rsp.put("products",criteriaResult);
*/

            return rsp;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;

        }
    }
}
