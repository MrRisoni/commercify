package core;

import entity.general.Currencies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repositories.CurrencyRepo;

@RestController

public class GeneralController {

    @Autowired
    CurrencyRepo curRepo;

    @RequestMapping(value=  "/api/currencies" , method = RequestMethod.GET)
    public Iterable<Currencies> getCurrencies() {
       return curRepo.findAll();
    }
}
