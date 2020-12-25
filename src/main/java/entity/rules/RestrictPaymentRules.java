package entity.rules;

import entity.order.PaymentMethods;
import entity.shop.Shops;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@Entity
@Table(name = "restrict_payment_rules")
public class RestrictPaymentRules {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column
    private String title;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @NotNull
    private boolean active;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ruleObj")
    private Collection<RestrictPaymentCriteria> criteria;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(
            name = "rules_disables_methods",
            joinColumns = @JoinColumn(name = "rule_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private List<PaymentMethods> disabledMethods = new ArrayList<>();

    public RestrictPaymentRules() {
    }

    public RestrictPaymentRules(Long id, @NotNull String title) {
        this.id = id;
        this.title = title;
    }

}
