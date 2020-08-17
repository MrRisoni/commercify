package core;


import entity.ProductCategories;
import entity.Shops;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repositories.ShopRepo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ShopController {

    @Autowired
    ShopRepo shRp;

    @RequestMapping(value=  "/api/shop/categories/{shopId}" , method = RequestMethod.GET)
    public Collection<ProductCategories> getShopInfo() {
        Optional<Shops> fetch= shRp.findById(2L);
        Shops magazi = fetch.orElse(null);
        return  magazi.getProductCategoriesCollection();
    }
}

