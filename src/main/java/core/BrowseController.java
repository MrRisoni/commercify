package core;

import dto.ProductPreview;
import entity.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.*;

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

            // for every filter make a query
            String sqlSSD= "SELECT product_id FROM product_attributes_values WHERE valueNumeric=1.0  and attribute_id=9";

            String sqlInches= " SELECT product_id FROM product_attributes_values WHERE valueNumeric<13  and attribute_id=7";
            int filtrCounter = 0;

            List<String> joinSql = new ArrayList<>();
            String filterId = "filtr" + String.valueOf(filtrCounter);
            joinSql.add("JOIN ( " + sqlSSD + " ) AS " + filterId + " ON "+filterId+".product_id=p.id");

            filtrCounter++;
            filterId = "filtr" + String.valueOf(filtrCounter);
            joinSql.add("JOIN ( " + sqlInches + " ) AS " + filterId+ " ON "+filterId+".product_id=p.id");

            // dynamic join....
            String productSQL= "SELECT p.id FROM products p ";
            String finalSQL = productSQL + String.join(" ",joinSql);

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
