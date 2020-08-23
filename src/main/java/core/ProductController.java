package core;

import entity.HibernateUtil;
import entity.JackSonViewer;
import entity.ProductAttributesValues;
import entity.Products;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pojo.JsonStringResponse;
import repositories.ProductRepo;
import repositories.ShopRepo;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    ProductRepo prodRp;


   @RequestMapping(value=  "/api/product/{productId}" , method = RequestMethod.GET)
    public String getProduct(@PathVariable Long productId)
    {
        try {
            HashMap<String, Object> rsp = new HashMap<>();

            Optional<Products> fetch = prodRp.findById(7L);
            Products proion = fetch.orElse(null);

           String jsonSerialize = HibernateUtil.getCustomMapper().writerWithView(JackSonViewer.IShopProduct.class).writeValueAsString(proion);
            return jsonSerialize;

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;

        }

    }


}
