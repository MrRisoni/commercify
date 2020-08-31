package core;

import com.google.gson.Gson;
import entity.HibernateUtil;
import entity.JackSonViewer;
import entity.order.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Basket;
import repositories.OrdersRepository;

import java.util.Optional;

@RestController
public class OrdersController {
    @Autowired
    OrdersRepository orderRepo;

    @RequestMapping(value=  "/api/order/place_order" , method = RequestMethod.POST)
    public String placeOrder( @RequestBody Object postData) {
        Gson g = new Gson();
        Basket kalathi = new Gson().fromJson(g.toJson(postData), Basket.class);

    }

    @RequestMapping(value = "/api/order/{orderId}", method = RequestMethod.GET)
    public String getOrderDetails(@PathVariable Long orderId) {
        try {
            Optional<Orders> fetch = orderRepo.findById(2L);
            Orders paragellia = fetch.orElse(null);

            return HibernateUtil.getCustomMapper().writerWithView(JackSonViewer.IOrder.class).writeValueAsString(paragellia);

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
