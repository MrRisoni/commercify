package services;

import dto.CustomerReport;
import dto.OrderCsvExportRow;
import entity.HibernateUtil;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

import java.util.List;

public class RevenueSrvc {

    public static List<CustomerReport> getCustomers(Long shopId) {
        String sql = "  SELECT u.id AS Id, " +
                " CONCAT(u.first_name,' ',u.last_name) AS fullName, " +
                " u.email AS email, " +
                " u.created AS createdAt, " +
                " totalNets.net AS netPrice, " +
                " totalNets.totalNum AS numOrders, " +
                " latestDates.latest  AS lastOrder " +
                " FROM users u " +
                " JOIN " +
                " ( " +
                "        SELECT user_id,COUNT(id) AS totalNum,SUM(currency_rate * net) AS net " +
                "        FROM orders " +
                "        WHERE shop_id =2 " +
                "       GROUP BY user_id " +
                " ) AS totalNets ON totalNets.user_id  = u.id " +
                " JOIN " +
                " ( " +
                "         SELECT user_id,MAX(created_at) AS latest " +
                "         FROM orders " +
                "         WHERE shop_id =2 " +
                "         GROUP BY user_id " +
                " ) AS latestDates ON latestDates.user_id = u.id ";

        return HibernateUtil.getEM().createNativeQuery(sql)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .addScalar("Id", StandardBasicTypes.LONG)
                .addScalar("numOrders", StandardBasicTypes.LONG)
                .addScalar("netPrice",StandardBasicTypes.BIG_DECIMAL)
                .addScalar("fullName",StandardBasicTypes.STRING)
                .addScalar("email",StandardBasicTypes.STRING)
                .addScalar("createdAt",StandardBasicTypes.DATE)
                .setResultTransformer(Transformers.aliasToBean(CustomerReport.class))
                .getResultList();
    }
}
