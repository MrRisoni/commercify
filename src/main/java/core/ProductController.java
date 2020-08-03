package core;

import entities.HibernateUtil;
import entities.Language;
import entities.Product;
import entities.ProductAttribute;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController {

    @RequestMapping(value=  "/api/product/{productId}/{lang}" , method = RequestMethod.GET)
    public List<ProductAttribute> getProduct(@PathVariable Long productId, @PathVariable String lang)
    {
        Session s = HibernateUtil.getSessionFactory().openSession();
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



        return qry.getResultList();

    }


}
