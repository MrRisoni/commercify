package controllers;

import dto.ProductsDto;
import entity.HibernateUtil;
import entity.product.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repositories.ProductRepo;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductRepo prodRp;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/api/product/{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductsDto> getProduct(@PathVariable Long productId) {
        try {
            HashMap<String, Object> rsp = new HashMap<>();

            Optional<Products> fetch = prodRp.findById(7L);
            Products productDB = fetch.orElse(null);

            ProductsDto product = new ProductsDto();
            product = modelMapper.map(productDB, ProductsDto.class);
            return new ResponseEntity<>(product, HttpStatus.BAD_GATEWAY);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }
    }

    @RequestMapping(value = "/api/products/list", method =RequestMethod.GET)
    public List<Products> getProducts()
    {
        try {
            return HibernateUtil.getEM().createQuery("SELECT new dto.ProductsDto(id,title, thumbnailUrl, price,kilos, stock, created, updated," +
                    " totalOrders, totalClicks, avgRating) FROM Products")
                    .getResultList();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
