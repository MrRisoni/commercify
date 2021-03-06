package services;

import core.Application;
import entity.HibernateUtil;
import entity.general.GlobeRegions;
import entity.order.BillingAddress;
import entity.order.ShippingAddress;
import entity.product.Products;
import entity.shop.Shops;
import org.junit.Test;
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

    @Test
    public void testOrderDoubleBassCheckVolume() {
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

        BasketItem itm = new BasketItem(new Products(4408L),2,4L); // double bass
        BasketItem itm2 = new BasketItem(new Products(4409L),1,3L); // trumpet

        List<BasketItem> items = new ArrayList<>();
        items.add(itm);
        items.add(itm2);

        basket.setItems(items);

        basket.setUpdatedAt(new Date());
        volService.setBasket(basket);
        volService.setEm(HibernateUtil.getEM());

        assertEquals(volService.getTotalVolumeShippingCosts().getShipCost(),new BigDecimal(23));

    }
}
