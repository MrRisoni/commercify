package logic;

import common.Utils;
import entity.order.PaymentMethods;
import entity.rules.RestrictPaymentCriteria;
import entity.rules.RestrictPaymentRules;
import pojo.Basket;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PaymentMethodsEvaluator {
    // by default all payment methods are available
    // user must define rules for disabling!!!

    private EntityManager em;

    public PaymentMethodsEvaluator(EntityManager em) {
        this.em = em;
    }

    public Set<String> getDisabledMethods(Basket basket) {
        List<RestrictPaymentRules> rulesList = this.em.createQuery("SELECT rul FROM " +
                "RestrictPaymentRules rul JOIN " +
                " rul.shopId sh WHERE sh.id = :shid AND rul.active = 1 ", RestrictPaymentRules.class)
                .setParameter("shid", basket.getShop().getId()).getResultList();

        Set<String> disabledCodes = new HashSet<>();

        BigDecimal paramValue = new BigDecimal(0);
        BigDecimal dbValue = new BigDecimal(0);

        for (RestrictPaymentRules rul : rulesList) {
            boolean ruleOK = true;

            for (RestrictPaymentCriteria criterion : rul.getCriteria()) {

                String parameterCode = criterion.getParameterObj().getCode();
                String operatorCode = criterion.getOperatorObj().getCode();

                System.out.println("OPERATOR " + operatorCode);
                System.out.println("PARAMETER " + parameterCode);

                switch (operatorCode) {
                    case "lt":
                    case "lte":
                    case "eq":
                    case "gt":
                    case "gte":
                        if (criterion.getParameterObj().getCode().equals("totalWeight")) {
                            paramValue = Utils.getTotalOrderWeight(this.em, basket);
                        }
                        dbValue = new BigDecimal(criterion.getValue());
                        if (!this.gte(paramValue, dbValue)) {
                            ruleOK = false;
                        }
                        break;
                }
            }
            if (ruleOK) {
               List<PaymentMethods> disables = rul.getDisabledMethods();
                for (PaymentMethods pm : disables) {
                    disabledCodes.add(pm.getCode());
                    System.out.println(pm.getCode());
                }
            }
        }

        return disabledCodes;
    }

    private boolean gte(BigDecimal paramValue, BigDecimal critThreshold) {
        int cmp = paramValue.compareTo(critThreshold);
        System.out.println("LEFT " + paramValue + " " + critThreshold);
        return (cmp >= 0);
    }

    private boolean eq(BigDecimal paramValue, BigDecimal critThreshold) {
        int cmp = paramValue.compareTo(critThreshold);
        return (cmp >= 0);
    }

    private boolean eq(String paramValue, String critThreshold) {
        int cmp = paramValue.compareTo(critThreshold);
        return (cmp >= 0);
    }
}