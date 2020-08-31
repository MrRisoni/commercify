package core;

import com.google.gson.Gson;
import entity.HibernateUtil;
import entity.JackSonViewer;
import entity.order.*;
import entity.shipping.ShopCourierClasses;
import entity.shop.Shops;
import entity.shop.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Basket;
import repositories.OrderStatusRepository;
import repositories.OrdersRepository;
import repositories.ProductRepo;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class OrdersController {

    @Autowired
    OrdersRepository orderRepo;

    @Autowired
    OrderStatusRepository ordStatusRepo;

    @Autowired
    ProductRepo prodRepo;

    @RequestMapping(value=  "/api/order/place_order" , method = RequestMethod.POST)
    public String placeOrder( @RequestBody Object postData) {
        Gson g = new Gson();
        Basket kalathi = new Gson().fromJson(g.toJson(postData), Basket.class);

        Orders paragelia = new Orders();
        paragelia.setShopId(new Shops(kalathi.getShop().getId()));
        paragelia.setBillingAddressId(new BillingAddress(kalathi.getBillTo().getId()));
        paragelia.setShippingAddressId(new ShippingAddress(kalathi.getShipTo().getId()));
        paragelia.setCurrency(kalathi.getCurrencyCode());
        paragelia.setCurrency_rate(new BigDecimal(1.0));
        paragelia.setShipClassId(new ShopCourierClasses(kalathi.getDeliveryClass().getId()));
        paragelia.setUserId(new Users(kalathi.getUsr().getId()));
        paragelia.setPayMethodId(new PaymentMethods(kalathi.getPay().getId()));
        OrderStatus std = ordStatusRepo.findOneByTitle("Pending");
        System.out.println("STATUS OF PENDING " + std.getId());
        paragelia.setStatusId(std);


        paragelia.setTax(new BigDecimal(0));
        paragelia.setTotal(new BigDecimal(0));
        paragelia.setNet(new BigDecimal(0));
        paragelia.setShipping(new BigDecimal(0));
        paragelia.setTax(new BigDecimal(0));
        paragelia.setCourrierFees(new BigDecimal(0));
        paragelia.setSuccess(false);
        paragelia.setRefund(false);
        paragelia.setVoid(false);


        // timeout for card process


        Orders savedOrder = orderRepo.save(paragelia);
        //update timestamp!!
        // set success true,

        return "ORDERID " + savedOrder.getId();

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
