package services;

import core.Application;
import entity.HibernateUtil;
import entity.general.GlobeRegions;
import entity.order.BillingAddress;
import entity.order.ShippingAddress;
import entity.product.Products;
import entity.shipping.ShopCourierClasses;
import entity.shop.Shops;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.Basket;
import pojo.BasketItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class VolumeServiceTest {


    public void testOrderTubaCheckVolume() {
        VolumeService volService = new VolumeService();

        BillingAddress billTo = new BillingAddress();
        billTo.setCountryCode("GR");
        billTo.setPostCode("28100");
        billTo.setRegionId(new GlobeRegions(2L));

        ShippingAddress shipTo = new ShippingAddress();
        shipTo.setCountryCode("GR");
        shipTo.setPostCode("28100");
        shipTo.setRegionId(new GlobeRegions(2L));

        Basket basket = new Basket();

        basket.setBillTo(billTo);
        basket.setShipTo(shipTo);
        basket.setShop(new Shops(3L));
        basket.setDeliveryClass(new ShopCourierClasses(1L));

        BasketItem itm = new BasketItem(new Products(4409L),1);

        List<BasketItem> items = new ArrayList<>();
        items.add(itm);

        basket.setItems(items);

        basket.setUpdatedAt(new Date());
        volService.setBasket(basket);
        volService.setEm(HibernateUtil.getEM());

        assertEquals(volService.getTotalShippingCosts().getShipCost(),new BigDecimal(23));

    }
}
