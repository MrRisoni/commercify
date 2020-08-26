package entity.rules;


import entity.shop.Shops;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restrict_payment_rules_criteria")
public class RestrictPaymentCriteria {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parameter_id", referencedColumnName = "id")
    private RestrictPaymentOperator operatorObj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "operator_id", referencedColumnName = "id")
    private RestrictPaymentParameters parameterObj;

    @Basic(optional = false)
    @NotNull
    @Column
    private String 	value;


    @JoinColumn(name = "rule_od", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RestrictPaymentRules ruleObj;

    public RestrictPaymentCriteria() {
    }

    public RestrictPaymentCriteria(Long id, RestrictPaymentOperator operatorObj, RestrictPaymentParameters parameterObj, @NotNull String value) {
        this.id = id;
        this.operatorObj = operatorObj;
        this.parameterObj = parameterObj;
        this.value = value;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RestrictPaymentOperator getOperatorObj() {
        return operatorObj;
    }

    public void setOperatorObj(RestrictPaymentOperator operatorObj) {
        this.operatorObj = operatorObj;
    }

    public RestrictPaymentParameters getParameterObj() {
        return parameterObj;
    }

    public void setParameterObj(RestrictPaymentParameters parameterObj) {
        this.parameterObj = parameterObj;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
