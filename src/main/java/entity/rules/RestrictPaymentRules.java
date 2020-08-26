package entity.rules;


import entity.order.PaymentMethods;
import entity.shop.Shops;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

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

    @Basic(optional = false)
    @NotNull
    @Column
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    public Collection<RestrictPaymentCriteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(Collection<RestrictPaymentCriteria> criteria) {
        this.criteria = criteria;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<PaymentMethods> getDisabledMethods() {
        return disabledMethods;
    }

    public void setDisabledMethods(List<PaymentMethods> disabledMethods) {
        this.disabledMethods = disabledMethods;
    }
}
