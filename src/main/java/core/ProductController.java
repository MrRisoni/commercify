package core;

import entities.HibernateUtil;
import entities.Language;
import entities.ProductAttribute;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.*;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController {


    @RequestMapping(value=  "/api/attributes/{productId}/{lang}" , method = RequestMethod.GET)
    public List<ProductAttribute> getAttributes(@PathVariable Long productId, @PathVariable String lang)
    {
        Session s = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ProductAttribute> cr = cb.createQuery(ProductAttribute.class);
        Root<ProductAttribute> riza = cr.from(ProductAttribute.class);
        Join<ProductAttribute, Language> langJoin = riza.join("langObj", JoinType.INNER);

        Predicate prdLang = cb.equal(riza.get("id"),productId);
        Predicate prdProduct = cb.equal(langJoin.get("code"),lang);
        Predicate allConditions = cb.conjunction();
        allConditions = cb.and(allConditions,prdLang);
        allConditions = cb.and(allConditions,prdProduct);

        cr.where(allConditions);
        cr.select(riza);

        Query<ProductAttribute> qry = s.createQuery(cr);

        // only for lang 2



        return qry.getResultList();

    }
}
