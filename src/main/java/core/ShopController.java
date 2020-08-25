package core;


import entity.HibernateUtil;
import entity.JackSonViewer;
import entity.product.ProductCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repositories.ShopRepo;

import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;

@RestController

public class ShopController {

    @Autowired
    ShopRepo shRp;

    @RequestMapping(value=  "/api/shop/categories/{shopId}" , method = RequestMethod.GET)
    public HashMap<String,Object> getShopInfo() {
        try {
           // Optional<Shops> fetch = shRp.findById(2L);
           // Shops magazi = fetch.orElse(null);
            HashMap<String, Object> rsp = new HashMap<>();

            TypedQuery<ProductCategories> query = HibernateUtil.getEM().createNamedQuery("ProductCategories_fetchByShopId",
                    ProductCategories.class);
            query.setParameter("shop_id", 2L);
            List<ProductCategories> result = query.getResultList();
            rsp.put("favorites",result);
            System.out.println("Count RESULT !!!" + result.size());
           rsp.put("favorites", HibernateUtil.getCustomMapper().writerWithView(JackSonViewer.IShopProductCategory.class).writeValueAsString(result));
            return rsp;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return  null;
        }
    }
}

