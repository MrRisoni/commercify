package controllers;

import dto.RestrictPaymentCriteriaDTO;
import entity.HibernateUtil;
import entity.order.PaymentMethods;
import entity.rules.RestrictPaymentOperator;
import entity.rules.RestrictPaymentParameters;
import entity.rules.RestrictPaymentRules;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repositories.OperatorRepo;
import repositories.ParameterRepo;

import java.util.List;

@RestController
public class RuleController {

    @Autowired
    OperatorRepo opRp;

    @Autowired
    ParameterRepo parRp;

    @RequestMapping(value=  "/api/restrict/criteria/{ruleId}" , method = RequestMethod.GET)
    public List<RestrictPaymentCriteriaDTO> getCriteria(@PathVariable Long ruleId)
    {
        return  HibernateUtil.getEM()
                .createNativeQuery("SELECT c.id , par.code AS parameterTitle, op.code AS operatorCode" +
                        " , c.value AS val " +
                        " FROM restrict_payment_rules_criteria c " +
                        " JOIN  restrict_payment_rules_operators op ON op.id = c.operator_id  " +
                        " JOIN  restrict_payment_parameters par ON par.id = c.parameter_id  " +
                        " WHERE c.rule_id = :rlid ")
                .setParameter("rlid",ruleId)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .addScalar("id", StandardBasicTypes.LONG)
                .addScalar("parameterTitle",StandardBasicTypes.STRING)
                .addScalar("operatorCode",StandardBasicTypes.STRING)
                .addScalar("val",StandardBasicTypes.STRING)
                .setResultTransformer(Transformers.aliasToBean(RestrictPaymentCriteriaDTO.class))
                .getResultList();
    }


    @RequestMapping(value=  "/api/restrict/operators" , method = RequestMethod.GET)
    public Iterable<RestrictPaymentOperator> getOperators()
    {
        return opRp.findAll();
    }

    @RequestMapping(value=  "/api/restrict/parameters" , method = RequestMethod.GET)
    public Iterable<RestrictPaymentParameters> getParameters()
    {
        return parRp.findAll();
    }

    @RequestMapping(value=  "/api/restrict/rules/{shopId}" , method = RequestMethod.GET)
    public List<RestrictPaymentRules> getPaymentRestrictionRules(@PathVariable Long shopId)
    {
        List<RestrictPaymentRules> rules =  HibernateUtil.getEM().createQuery("SELECT new entity.rules.RestrictPaymentRules(rul.id,rul.title) FROM " +
                "RestrictPaymentRules rul JOIN " +
                " rul.shopId sh WHERE sh.id = :shid AND rul.active = 1 ", RestrictPaymentRules.class)
                .setParameter("shid", shopId).getResultList();

        for (RestrictPaymentRules rl : rules) {
           List<PaymentMethods> disbl = HibernateUtil.getEM()
                   .createNativeQuery("SELECT d.payment_method_id AS id, p.code " +
                   " FROM rules_disables_methods d" +
                   " JOIN  payment_methods p ON p.id = d.payment_method_id " +
                   " WHERE d.rule_id = :rlid ")
                   .setParameter("rlid",rl.getId())
                   .unwrap(org.hibernate.query.NativeQuery.class)
                   .addScalar("id", StandardBasicTypes.LONG)
                   .addScalar("code",StandardBasicTypes.STRING)
                   .setResultTransformer(Transformers.aliasToBean(PaymentMethods.class))
                   .getResultList();
           rl.setDisabledMethods(disbl);
        }

        return rules;
    }
}
