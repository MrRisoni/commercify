package logic;

import common.Utils;
import entity.order.PaymentMethods;
import entity.order.ShippingAddress;
import entity.rules.RestrictPaymentCriteria;
import entity.rules.RestrictPaymentRules;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.Basket;
import repositories.ShipAddressRepo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.*;

public class PaymentMethodsEvaluator {
    // by default all payment methods are available
    // user must define rules for disabling!!!

    @Autowired
    ShipAddressRepo shipAddressRepo;

    private EntityManager em;

    public PaymentMethodsEvaluator(EntityManager em) {
        this.em = em;
    }

    public Set<String> getDisabledMethods(Basket basket) {

        Utils.setEm(this.em);

        List<RestrictPaymentRules> rulesList = this.em.createQuery("SELECT rul FROM " +
                "RestrictPaymentRules rul JOIN " +
                " rul.shopId sh WHERE sh.id = :shid AND rul.active = 1 ", RestrictPaymentRules.class)
                .setParameter("shid", basket.getShop().getId()).getResultList();

        Set<String> disabledCodes = new HashSet<>();

        BigDecimal paramValue = new BigDecimal(0);
        BigDecimal dbValue = new BigDecimal(0);
        Long longVal = 0L;
        String strVal = "";

        Optional<ShippingAddress> shipppedOptional = shipAddressRepo.findById(basket.getShipTo().getId());
        ShippingAddress savedShipAddress  = shipppedOptional.orElse(null);

        String phone = savedShipAddress.getPhone;

        for (RestrictPaymentRules rul : rulesList) {
            boolean ruleOK = true;

            for (RestrictPaymentCriteria criterion : rul.getCriteria()) {

                boolean stringCmp = false;
                boolean decimCmp = false;
                boolean longCmp = false;

                String parameterCode = criterion.getParameterObj().getCode();
                String operatorCode = criterion.getOperatorObj().getCode();

                System.out.println("OPERATOR " + operatorCode);
                System.out.println("PARAMETER " + parameterCode);

                if (criterion.getParameterObj().getCode().equals("totalWeight")) {
                    decimCmp = true;
                    paramValue = Utils.getTotalOrderWeight(basket);
                }

                if (criterion.getParameterObj().getCode().equals("totalNet")) {
                    decimCmp = true;
                    paramValue = Utils.getTotalNetOfOrder(basket);
                }

                if (criterion.getParameterObj().getCode().equals("productCatId")) {

                    longCmp = true;
                }

                if (criterion.getParameterObj().getCode().equals("productId")) {
                    longCmp = true;
                }

                if (criterion.getParameterObj().getCode().equals("phone")) {

                    stringCmp = true;
                }

                if (criterion.getParameterObj().getCode().equals("email")) {
                    stringCmp = true;
                }


                if (criterion.getParameterObj().getCode().equals("currency")) {
                    stringCmp = true;
                }

                if (criterion.getParameterObj().getCode().equals("zip")) {
                    stringCmp = true;

                }

                if (criterion.getParameterObj().getCode().equals("regionId")) {
                    longCmp = true;

                }

                if (criterion.getParameterObj().getCode().equals("countryCo")) {
                    stringCmp = true;

                }

                if (criterion.getParameterObj().getCode().equals("shipClass")) {
                    longCmp = true;

                }


                switch (operatorCode) {
                    case "lt":
                    case "lte":
                    case "eq":
                    case "gt":
                    case "gte":
                        if (criterion.getParameterObj().getCode().equals("totalWeight")) {
                            paramValue = Utils.getTotalOrderWeight(basket);
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

    private boolean lt(BigDecimal paramValue, BigDecimal critThreshold) {
        int cmp = paramValue.compareTo(critThreshold);
        System.out.println("LEFT " + paramValue + " " + critThreshold);
        return (cmp < 0);
    }

    private boolean lte(BigDecimal paramValue, BigDecimal critThreshold) {
        int cmp = paramValue.compareTo(critThreshold);
        System.out.println("LEFT " + paramValue + " " + critThreshold);
        return (cmp <=0);
    }

    private boolean gt(BigDecimal paramValue, BigDecimal critThreshold) {
        int cmp = paramValue.compareTo(critThreshold);
        System.out.println("LEFT " + paramValue + " " + critThreshold);
        return (cmp >= 0);
    }

    private boolean gte(BigDecimal paramValue, BigDecimal critThreshold) {
        int cmp = paramValue.compareTo(critThreshold);
        System.out.println("LEFT " + paramValue + " " + critThreshold);
        return (cmp >= 0);
    }

    private boolean eq(BigDecimal paramValue, BigDecimal critThreshold) {
        int cmp = paramValue.compareTo(critThreshold);
        return (cmp == 0);
    }

    private boolean eq(Long paramValue, Long critThreshold) {
      return paramValue == critThreshold;
    }

    private boolean eq(String paramValue, String critThreshold) {
        int cmp = paramValue.compareTo(critThreshold);
        return (cmp >= 0);
    }
}