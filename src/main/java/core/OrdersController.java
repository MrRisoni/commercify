package core;

import com.google.gson.Gson;
import entity.HibernateUtil;
import entity.JackSonViewer;
import entity.order.*;
import entity.product.Products;
import entity.shipping.ShopCourierClasses;
import entity.shop.Shops;
import entity.shop.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Basket;
import pojo.BasketItem;
import repositories.*;
import services.ShippingService;
import services.TaxSrvc;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class OrdersController {

    @Autowired
    OrdersRepository orderRepo;

    @Autowired
    OrderStatusRepository ordStatusRepo;

    @Autowired
    OrderStatusHistoryRepository ordStatusHistoryRepo;


    @Autowired
    BillAddressRepo billAddressRepo;

    @Autowired
    ShipAddressRepo shipAddressRepo;

    @RequestMapping(value=  "/api/order/place_order" , method = RequestMethod.POST)
    public String placeOrder( @RequestBody Object postData) {
        EntityManager entityManager = HibernateUtil.getEM();

        Gson g = new Gson();
        Basket kalathi = new Gson().fromJson(g.toJson(postData), Basket.class);

        Optional<BillingAddress> billedOptional = billAddressRepo.findById(kalathi.getBillTo().getId());
        BillingAddress savedBilledAddress  = billedOptional.orElse(null);

        Optional<ShippingAddress> shipppedOptional = shipAddressRepo.findById(kalathi.getShipTo().getId());
        ShippingAddress savedShipAddress  = shipppedOptional.orElse(null);

        kalathi.setBillTo(savedBilledAddress);
        kalathi.setShipTo(savedShipAddress);

        Orders paragelia = new Orders();
        paragelia.setShopId(new Shops(kalathi.getShop().getId()));
        paragelia.setBillingAddressId(savedBilledAddress);
        paragelia.setShippingAddressId(savedShipAddress);
        paragelia.setCurrency(kalathi.getCurrencyCode());
        paragelia.setCurrency_rate(new BigDecimal(1.0));
        paragelia.setShipClassId(new ShopCourierClasses(kalathi.getDeliveryClass().getId()));
        paragelia.setUserId(new Users(kalathi.getUsr().getId()));
        paragelia.setPayMethodId(new PaymentMethods(kalathi.getPay().getId()));
        OrderStatus std = ordStatusRepo.findOneByTitle("Pending");
        System.out.println("STATUS OF PENDING " + std.getId());
        paragelia.setStatusId(std);

        BigDecimal ttl = new BigDecimal(0);
        BigDecimal net = new BigDecimal(0);

        ShippingService shipService = new ShippingService();
        TaxSrvc taxService = new TaxSrvc();

        shipService.setBasket(kalathi);
        shipService.setEm(entityManager);

        taxService.setBasket(kalathi);
        taxService.setEm(entityManager);

        BigDecimal tax = taxService.getTotalTax();
        BigDecimal shippingCost = shipService.getTotalShippingCosts().getShipCost();


        BigDecimal courierFees = new BigDecimal(0);


        for (BasketItem itm : kalathi.getItems()) {

            Products gefundenProduct  = entityManager.createQuery("SELECT new entity.product.Products(p.id,p.price) " +
                    " FROM Products p WHERE p.id=:productId",Products.class)
                    .setParameter("productId",itm.getProd().getId())
                    .getSingleResult();

            net =net.add(gefundenProduct.getPrice());
        }

        BigDecimal commission  = net.multiply(new BigDecimal(0.1));

        ttl = ttl.add(tax);
        ttl = ttl.add(shippingCost);
        ttl = ttl.add(net);
        ttl = ttl.add(courierFees);

        paragelia.setCommission(commission);
        paragelia.setShipping(shippingCost);
        paragelia.setTax(tax);
        paragelia.setNet(net);
        paragelia.setCourrierFees(new BigDecimal(0));
        paragelia.setTotal(ttl);

        paragelia.setSuccess(false);
        paragelia.setRefund(false);
        paragelia.setVoid(false);


        // timeout for card process


        Orders savedOrder = orderRepo.save(paragelia);

        OrderStatusHistory orderStatusHistoryObj = new OrderStatusHistory();
        orderStatusHistoryObj.setStatusObj(std);
        orderStatusHistoryObj.setOrderObj(savedOrder);
        ordStatusHistoryRepo.save(orderStatusHistoryObj);
        //update timestamp!!
        // set success true,


        //save order Items

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
