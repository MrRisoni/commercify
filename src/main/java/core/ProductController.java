package core;

import entity.HibernateUtil;
import entity.JackSonViewer;
import entity.product.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.ProductRepo;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductRepo prodRp;

    @RequestMapping(value = "/api/product/{productId}", method = RequestMethod.GET)
    public ResponseEntity<String> getProduct(@PathVariable Long productId) {
        try {
            HashMap<String, Object> rsp = new HashMap<>();

            Optional<Products> fetch = prodRp.findById(7L);
            Products proion = fetch.orElse(null);

            String jsonSerialize = HibernateUtil.getCustomMapper().writerWithView(JackSonViewer.IShopProduct.class).writeValueAsString(proion);
            return new ResponseEntity<>(jsonSerialize, HttpStatus.BAD_GATEWAY);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }
}
