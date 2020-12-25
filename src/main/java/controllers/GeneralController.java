package controllers;

import entity.HibernateUtil;
import entity.general.Currencies;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repositories.CurrencyRepo;

import java.util.HashMap;
import java.util.List;

@RestController
public class GeneralController {

    @Autowired
    CurrencyRepo curRepo;

    @RequestMapping(value=  "/api/currencies" , method = RequestMethod.GET)
    public ResponseEntity<List<Currencies>> getCurrencies() {
        try {
            return new ResponseEntity<>( HibernateUtil.getEM().createQuery("SELECT new entity.general.Currencies(id,title,code) FROM Currencies",Currencies.class).getResultList(), HttpStatus.OK);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }
    }


    @RequestMapping(value=  "/api/version" , method = RequestMethod.GET)
    public ResponseEntity<String> getVersion() {

        return new ResponseEntity<>("4.5.0.11", HttpStatus.OK);
        
    }
}
