package core;

import entity.general.Currencies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repositories.CurrencyRepo;

import java.util.HashMap;

@RestController
public class GeneralController {

    @Autowired
    CurrencyRepo curRepo;

    @RequestMapping(value=  "/api/currencies" , method = RequestMethod.GET)
    public ResponseEntity<Object> getCurrencies() {
        try {
            return new ResponseEntity<>(curRepo.findAll(), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }


    @RequestMapping(value=  "/api/version" , method = RequestMethod.GET)
    public ResponseEntity<String> getVersion() {
        
        return new ResponseEntity<>("4.5.0.11", HttpStatus.OK);
        
    }
}
