package core;

import entities.HibernateUtil;
import entities.ProductAttribute;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController {


    @RequestMapping(value=  "/api/shop" , method = RequestMethod.GET)
    public List<ProductAttribute> getAttributes()
    {
        Session s = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<ProductAttribute> cr = cb.createQuery(ProductAttribute.class);
        Root<ProductAttribute> riza = cr.from(ProductAttribute.class);
        cr.select(riza);

        Query<ProductAttribute> qry = s.createQuery(cr);

        // only for lang 2


        s.close();
        return qry.getResultList();

    }
}
