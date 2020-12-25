package controllers.exports;


import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import dto.OrderCsvExportRow;
import entity.HibernateUtil;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController

public class ExportController {

   /* @RequestMapping(value = "/api/export/customers", method = RequestMethod.GET)
    public void getCustomers(HttpServletResponse response) {
        RevenueSrvc.getCustomers();
    }*/

    @RequestMapping(value = "/api/export/orders", method = RequestMethod.GET)
    public void getOrders(HttpServletResponse response) {

        try {

            List<OrderCsvExportRow> data= HibernateUtil.getEM().createNativeQuery("SELECT COUNT(id) AS numOrders," +
                    "  SUM(net) AS netPrice," +
                    " CONCAT(MONTH(created_at),'-',YEAR(created_at)) AS period" +
                    " FROM orders" +
                    " WHERE success =1" +
                    " GROUP BY  CONCAT(MONTH(created_at),'-',YEAR(created_at))" +
                    " ORDER BY YEAR(created_at), MONTH(created_at) ASC")
                    .unwrap(org.hibernate.query.NativeQuery.class)
                    .addScalar("numOrders", StandardBasicTypes.LONG)
                    .addScalar("netPrice",StandardBasicTypes.BIG_DECIMAL)
                    .addScalar("period",StandardBasicTypes.STRING)
                    .setResultTransformer(Transformers.aliasToBean(OrderCsvExportRow.class))
                    .getResultList();

            String filename = "orders.csv";

            response.setContentType("text/csv");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + filename + "\"");

            //create a csv writer
            StatefulBeanToCsv<OrderCsvExportRow> writer = new StatefulBeanToCsvBuilder<OrderCsvExportRow>(response.getWriter())
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withOrderedResults(false)
                    .build();

            //write all users to csv file
            writer.write(data);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
