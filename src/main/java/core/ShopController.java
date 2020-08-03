package core;

import entities.Currency;
import entities.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repositories.ShopRepo;

import java.util.Optional;

@RestController
@CrossOrigin
public class ShopController {

    @Autowired
    ShopRepo shRp;

    @RequestMapping(value=  "/api/shop" , method = RequestMethod.GET)
    public Shop getShopInfo() {
        Optional<Shop> fetch= shRp.findById(1L);

        return fetch.orElse(null);
    }
}

