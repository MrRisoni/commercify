package controllers;

import com.google.gson.Gson;
import entity.HibernateUtil;
import entity.order.BillingAddress;
import entity.order.ShippingAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pojo.Basket;
import pojo.TaxInfo;
import repositories.BillAddressRepo;
import repositories.ShipAddressRepo;
import services.ShippingService;
import services.TaxSrvc;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@RestController
public class TaxController {

    @Autowired
    BillAddressRepo billAddressRepo;

    @Autowired
    ShipAddressRepo shipAddressRepo;

    @RequestMapping(value = "/api/order/tax", method = RequestMethod.POST)
    public BigDecimal getShipCost(@RequestBody Object postData) {
        EntityManager entityManager = HibernateUtil.getEM();
        ShippingService shipService = new ShippingService();
        Gson g = new Gson();
        Basket kalathi = new Gson().fromJson(g.toJson(postData), Basket.class);
        kalathi.setUpdatedAt(new Date());

        Optional<BillingAddress> billedOptional = billAddressRepo.findById(kalathi.getBillTo().getId());
        BillingAddress savedBilledAddress  = billedOptional.orElse(null);

        Optional<ShippingAddress> shipppedOptional = shipAddressRepo.findById(kalathi.getShipTo().getId());
        ShippingAddress savedShipAddress  = shipppedOptional.orElse(null);

        kalathi.setBillTo(savedBilledAddress);
        kalathi.setShipTo(savedShipAddress);

        TaxSrvc taxService = new TaxSrvc();
        taxService.setBasket(kalathi);
        taxService.setEm(entityManager);
        TaxInfo tax_info = taxService.getTotalTaxData();

        BigDecimal tax =tax_info.getTotalTax();
        HibernateUtil.getEM().close();
        return tax;
    }
}
