package entity.rules;

import entity.shop.Shops;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "restrict_payment_rules_criteria")
public class RestrictPaymentCriteria {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "operator_id", referencedColumnName = "id")
    private RestrictPaymentOperator operatorObj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parameter_id", referencedColumnName = "id")
    private RestrictPaymentParameters parameterObj;

    @Basic(optional = false)
    @NotNull
    @Column
    private String value;

    @JoinColumn(name = "rule_id", referencedColumnName = "id")
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
}