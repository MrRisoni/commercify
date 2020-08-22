package services;

import core.Application;
import entity.*;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import pojo.Basket;
import pojo.BasketItem;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TaxServiceTest {


    @Test
    public void testSampleService() {
        TaxSrcv taxService = new TaxSrcv();
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
        basket.setShipTop(shipTo);
        basket.setShop(new Shops(2L));

        BasketItem itm = new BasketItem(new Products(1L),2);
        BasketItem itm2 = new BasketItem(new Products(2L),2);

        List<BasketItem> items = new ArrayList<>();
        items.add(itm);
        items.add(itm2);

        basket.setItems(items);

        taxService.setBasket(basket);
        taxService.setEm(HibernateUtil.getEM());

        assertEquals(taxService.getTotalTax(),new BigDecimal(23));
    }
}
