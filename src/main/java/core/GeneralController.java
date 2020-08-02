package core;

import entities.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repositories.CurrencyRepo;

import java.util.Optional;

@RestController
@CrossOrigin
public class GeneralController {

    @Autowired
    CurrencyRepo curRepo;

    @RequestMapping(value=  "/api/currencies" , method = RequestMethod.GET)
    public Iterable<Currency> getCurrencies() {
       return curRepo.findAll();
    }
}
