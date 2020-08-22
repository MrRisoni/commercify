
package entity;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "shop_weight_ship_rules")
public class ShopWeightShipRules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column(name = "title")
    private String title;

    @Basic(optional = false)
    @NotNull
    @Column(name = "taxable")
    private boolean taxable;

    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "less_than_kg")
    private BigDecimal lessThanKg;

    @Basic(optional = false)
    @NotNull
    @Column(name = "less_equal")
    private boolean lessEqual;

    @Basic(optional = false)
    @NotNull
    @Column(name = "over_than_kg")
    private BigDecimal overThanKg;

    @Basic(optional = false)
    @NotNull
    @Column(name = "over_equal")
    private boolean overEqual;

    @Basic(optional = false)
    @NotNull
    @Column(name = "base_cost")
    private BigDecimal baseCost;

    @Basic(optional = false)
    @NotNull
    @Column(name = "charge")
    private BigDecimal charge;

    @Basic(optional = false)
    @NotNull
    @Column(name = "over_total_weight")
    private BigDecimal overTotalWeight;

    @Basic(optional = false)
    @NotNull
    @Column(name = "for_each_kg")
    private BigDecimal forEachKg;

    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @JoinColumn(name = "shipping_class_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopCourierClasses shippingClassId;

    public ShopWeightShipRules() {
    }

    public ShopWeightShipRules(Long id) {
        this.id = id;
    }

    public ShopWeightShipRules(Long id, String title, boolean taxable, Date createdAt, BigDecimal lessThanKg, boolean lessEqual, BigDecimal overThanKg, boolean overEqual, BigDecimal baseCost, BigDecimal charge, BigDecimal overTotalWeight, BigDecimal forEachKg, boolean active) {
        this.id = id;
        this.title = title;
        this.taxable = taxable;
        this.createdAt = createdAt;
        this.lessThanKg = lessThanKg;
        this.lessEqual = lessEqual;
        this.overThanKg = overThanKg;
        this.overEqual = overEqual;
        this.baseCost = baseCost;
        this.charge = charge;
        this.overTotalWeight = overTotalWeight;
        this.forEachKg = forEachKg;
        this.active = active;
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

    public boolean getTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getLessThanKg() {
        return lessThanKg;
    }

    public void setLessThanKg(BigDecimal lessThanKg) {
        this.lessThanKg = lessThanKg;
    }

    public boolean getLessEqual() {
        return lessEqual;
    }

    public void setLessEqual(boolean lessEqual) {
        this.lessEqual = lessEqual;
    }

    public BigDecimal getOverThanKg() {
        return overThanKg;
    }

    public void setOverThanKg(BigDecimal overThanKg) {
        this.overThanKg = overThanKg;
    }

    public boolean getOverEqual() {
        return overEqual;
    }

    public void setOverEqual(boolean overEqual) {
        this.overEqual = overEqual;
    }

    public BigDecimal getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(BigDecimal baseCost) {
        this.baseCost = baseCost;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public BigDecimal getOverTotalWeight() {
        return overTotalWeight;
    }

    public void setOverTotalWeight(BigDecimal overTotalWeight) {
        this.overTotalWeight = overTotalWeight;
    }

    public BigDecimal getForEachKg() {
        return forEachKg;
    }

    public void setForEachKg(BigDecimal forEachKg) {
        this.forEachKg = forEachKg;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    public ShopCourierClasses getShippingClassId() {
        return shippingClassId;
    }

    public void setShippingClassId(ShopCourierClasses shippingClassId) {
        this.shippingClassId = shippingClassId;
    }
}
