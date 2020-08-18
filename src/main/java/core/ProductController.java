package core;

import entity.HibernateUtil;
import entity.JackSonViewer;
import entity.ProductAttributesValues;
import entity.Products;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
       /* Session s = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ProductAttribute> cr = cb.createQuery(ProductAttribute.class);
        Root<ProductAttribute> riza = cr.from(ProductAttribute.class);
        Join<Product, ProductAttribute> productJoin = riza.join("productObj", JoinType.INNER);
        Join<ProductAttribute, Language> langJoin = riza.join("langObj", JoinType.INNER);

        // ERROR
        Predicate prd = cb.equal(productJoin.get("id"),productId);
        Predicate prdLang = cb.equal(langJoin.get("code"),lang);
        Predicate allConditions =  cb.and(prdLang,prd);


        cr.where(allConditions);
        cr.select(riza);

        Query<ProductAttribute> qry = s.createQuery(cr);

        // only for lang 2
*/

        try {
            HashMap<String, Object> rsp = new HashMap<>();

            Optional<Products> fetch = prodRp.findById(7L);
            Products proion = fetch.orElse(null);

           // rsp.put("favorites", HibernateUtil.getCustomMapper().writerWithView(JackSonViewer.IShopProduct.class).writeValueAsString(proion));
            return HibernateUtil.getCustomMapper().writerWithView(JackSonViewer.IShopProduct.class).writeValueAsString(proion);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;

        }

    }


}
