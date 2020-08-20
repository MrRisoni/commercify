package core.analytics;

import dto.OrderReport;
import entity.HibernateUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class RevenueController {

    @RequestMapping(value="/api/revenue/orders",method = RequestMethod.GET)
    public List<OrderReport> getOrders() {
        try {
            return HibernateUtil.getEM().createQuery("SELECT new dto.OrderReport(o.id, concat(u.firstName,' ',u.lastName),o.createdAt,st.title, o.currency,o.total) " +
                    " FROM Orders o" +
                    " JOIN o.userId u " +
                    " JOIN o.statusId st" +
                    " WHERE o.success =1 ").getResultList();
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    @RequestMapping(value="/api/revenue/index",method=RequestMethod.GET)
    public HashMap<String, BigDecimal> index()
    {
        try {
            HashMap<String,BigDecimal> rsp = new HashMap<>();

            String sql = " SELECT o.currency_rate, SUM(o.total) AS total , " +
                    " SUM( o.net) AS net, SUM(o.tax) AS taxes,SUM(o.shipping) AS ship " +
                    " FROM orders o " +
                    " JOIN order_items oi ON oi.order_id = o.id " +
                    " WHERE o.shop_id = 2 " +
                    " AND o.success =1 " +
                    " AND o.void =0 " +
                    " GROUP BY oi.order_id, o.currency ";

            BigDecimal gross = new BigDecimal(0);
            BigDecimal net = new BigDecimal(0);
            BigDecimal taxes = new BigDecimal(0);
            BigDecimal ship = new BigDecimal(0);
            BigDecimal rate = new BigDecimal(0);

            List<Object[]> result = HibernateUtil.getEM().createNativeQuery(sql).getResultList();

            for (Object[] record : result) {
               // $gross += (float)$obj['total'] * $obj['currency_rate'];
                rate = new BigDecimal(record[0].toString());
                gross = gross.add((new BigDecimal(record[1].toString())).multiply(rate));
                net = net.add((new BigDecimal(record[2].toString())).multiply(rate));
                taxes = taxes.add((new BigDecimal(record[3].toString())).multiply(rate));
                ship = ship.add((new BigDecimal(record[4].toString())).multiply(rate));

            }

            rsp.put("gross",gross);
            rsp.put("net",net);
            rsp.put("taxes",taxes);
            rsp.put("ship",ship);

            return rsp;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
