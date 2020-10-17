package core;

import com.google.gson.Gson;
import common.Utils;
import dto.OrderCsvExportRow;
import dto.OrderDto;
import entity.HibernateUtil;
import entity.JackSonViewer;
import entity.order.*;
import entity.product.Products;
import entity.shipping.ShopCourierClasses;
import entity.shop.Shops;
import entity.shop.Users;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.Basket;
import pojo.BasketItem;
import pojo.TaxInfo;
import repositories.*;
import services.ShippingService;
import services.TaxSrvc;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class OrdersController {

    @Autowired
    OrderItemRepository orderItmRepo;

    @Autowired
    OrdersRepository orderRepo;

    @Autowired
    OrderStatusRepository ordStatusRepo;

    @Autowired
    OrderStatusHistoryRepository ordStatusHistoryRepo;

    @Autowired
    OrderItemStatusHistoryRepository ordItmStatusHistoryRepo;

    @Autowired
    ProductRepo prdRepo;

    @Autowired
    BillAddressRepo billAddressRepo;

    @Autowired
    ShipAddressRepo shipAddressRepo;

    @RequestMapping(value=  "/api/order/place_order" , method = RequestMethod.POST)
    public ResponseEntity<String> placeOrder(@RequestBody Object postData) {
        try {
            EntityManager entityManager = HibernateUtil.getEM();

            Gson g = new Gson();
            Basket kalathi = new Gson().fromJson(g.toJson(postData), Basket.class);

            kalathi.setUpdatedAt(new Date());
            Optional<BillingAddress> billedOptional = billAddressRepo.findById(kalathi.getBillTo().getId());
            BillingAddress savedBilledAddress = billedOptional.orElse(null);

            Optional<ShippingAddress> shipppedOptional = shipAddressRepo.findById(kalathi.getShipTo().getId());
            ShippingAddress savedShipAddress = shipppedOptional.orElse(null);

            kalathi.setBillTo(savedBilledAddress);
            kalathi.setShipTo(savedShipAddress);

            Orders paragelia = new Orders();
            paragelia.setShopId(new Shops(kalathi.getShop().getId()));
            paragelia.setBillingAddressId(savedBilledAddress);
            paragelia.setShippingAddressId(savedShipAddress);
            paragelia.setCurrency(kalathi.getCurrencyCode());
            paragelia.setCurrency_rate(new BigDecimal(1.0));
            paragelia.setUserId(new Users(kalathi.getUsr().getId()));
            paragelia.setPayMethodId(new PaymentMethods(kalathi.getPay().getId()));
            OrderStatus stdPending = ordStatusRepo.findOneByTitle("Pending");
            System.out.println("STATUS OF PENDING " + stdPending.getId());
            paragelia.setStatusId(stdPending);

            BigDecimal ttl = new BigDecimal(0);

            ShippingService shipService = new ShippingService();
            TaxSrvc taxService = new TaxSrvc();

            shipService.setBasket(kalathi);
            shipService.setEm(entityManager);

            taxService.setBasket(kalathi);
            taxService.setEm(entityManager);

            TaxInfo tax_info = taxService.getTotalTaxData();
            BigDecimal tax = tax_info.getTotalTax();
            BigDecimal shippingCost = shipService.getTotalWeightShippingCosts().getShipCost();

            BigDecimal courierFees = new BigDecimal(0);

            Utils.setEm(entityManager);
            BigDecimal net = Utils.getTotalNetOfOrder(kalathi);

            BigDecimal commission = net.multiply(new BigDecimal(0.1));

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

            Orders savedOrder = orderRepo.save(paragelia);

            OrderStatusHistory orderStatusHistoryObj = new OrderStatusHistory();
            orderStatusHistoryObj.setStatusObj(stdPending);
            orderStatusHistoryObj.setOrderObj(savedOrder);
            ordStatusHistoryRepo.save(orderStatusHistoryObj);

            //save order Items
            for (BasketItem proion : kalathi.getItems()) {
                System.out.println("OrderCtrl saving item " + proion.getProd().getId());
                OrderItems itemOrdered = new OrderItems();
                itemOrdered.setOrderId(savedOrder);
                itemOrdered.setQuantity(proion.getQuantity());

                Optional<Products> productOptional = prdRepo.findById(proion.getProd().getId());
                Products geFundenProduct = productOptional.orElse(null);
                itemOrdered.setProductId(geFundenProduct);
                itemOrdered.setStatusId(stdPending);
                itemOrdered.setNetPrice(geFundenProduct.getPrice());
                BigDecimal localTax =tax_info.getTaxPerProduct().get(proion.getProd().getId());
                if (localTax == null) {
                    localTax = new BigDecimal(0);
                }
                itemOrdered.setTaxes(localTax);
                itemOrdered.setShipClassId(new ShopCourierClasses(proion.getShipClassId()));
                System.out.println("itm to save has id " + itemOrdered.getProductId().getId());
                OrderItems savedItemOrdered = orderItmRepo.save(itemOrdered);

                OrderItemStatusHistory orderedItmStatusHistoryObj = new OrderItemStatusHistory();
                orderedItmStatusHistoryObj.setStatusObj(stdPending);
                orderedItmStatusHistoryObj.setItemObj(savedItemOrdered);
                orderedItmStatusHistoryObj.setCreatedAt(new Date());
                ordItmStatusHistoryRepo.save(orderedItmStatusHistoryObj);

            }


            // timeout for card process
            //update timestamp!!
            // set success true,
            // Optional<Orders> ordersOptional = orderRepo.findById(savedOrder.getId());
            //  Orders foundOrder  = ordersOptional.orElse(null);

            savedOrder.setSuccess(true);
            savedOrder.setUpdatedAt(new Date());
            orderRepo.save(savedOrder);

            return new ResponseEntity<>(savedOrder.getId().toString(), HttpStatus.OK);
        }
        catch (Exception ex)
        {
            System.out.println("OrdCtrl Exception");
            ex.printStackTrace();
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
        }

    }

    @RequestMapping(value = "/api/order/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<String> getOrderDetails(@PathVariable Long orderId) {
        try {
            Optional<Orders> fetch = orderRepo.findById(2L);
            Orders paragellia = fetch.orElse(null);

            String json = HibernateUtil.getCustomMapper().writerWithView(JackSonViewer.IOrder.class).writeValueAsString(paragellia);
            return new ResponseEntity<>(json, HttpStatus.BAD_GATEWAY);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(value = "/api/order/list",method = RequestMethod.GET)
    public List<OrderDto> getOrdersList()
    {
        EntityManager entityManager = HibernateUtil.getEM();
        Long userId = 2L;
        List<OrderDto> lista = entityManager.createNativeQuery("SELECT o.id AS orderId," +
                "  o.total,o.net,o.commission, o.tax AS taxes, o.shipping AS ship" +
                " FROM orders o ")
                .unwrap(org.hibernate.query.NativeQuery.class)
                .addScalar("orderId", StandardBasicTypes.LONG)
                .addScalar("total",StandardBasicTypes.BIG_DECIMAL)
                .addScalar("net",StandardBasicTypes.BIG_DECIMAL)
                .addScalar("commission",StandardBasicTypes.BIG_DECIMAL)
                .addScalar("taxes",StandardBasicTypes.BIG_DECIMAL)
                .addScalar("ship",StandardBasicTypes.BIG_DECIMAL)
                .setResultTransformer(Transformers.aliasToBean(dto.OrderDto.class))
                .getResultList();
        return lista;

    }
}
