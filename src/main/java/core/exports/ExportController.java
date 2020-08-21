package core.exports;


import dto.OrderCsvExportRow;
import entity.HibernateUtil;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ExportController {

    @RequestMapping(value = "/api/export/orders", method = RequestMethod.GET)
    public List getOrders() {

        try {

            return HibernateUtil.getEM().createNativeQuery("SELECT COUNT(id) AS numOrders," +
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
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
