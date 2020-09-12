package core;

import com.google.gson.Gson;
import entity.HibernateUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pojo.Basket;
import services.ShippingService;
import services.TaxSrvc;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@RestController
public class ShippingController {

    @RequestMapping(value = "/api/order/shipping_cost", method = RequestMethod.POST)
    public BigDecimal getShipCost(@RequestBody Object postData) {
        EntityManager entityManager = HibernateUtil.getEM();
        ShippingService shipService = new ShippingService();
        Gson g = new Gson();
        Basket kalathi = new Gson().fromJson(g.toJson(postData), Basket.class);

        shipService.setBasket(kalathi);
        shipService.setEm(entityManager);
        BigDecimal shippingCost = shipService.getTotalShippingCosts().getShipCost();
        HibernateUtil.getEM().close();
        return shippingCost;
    }
}