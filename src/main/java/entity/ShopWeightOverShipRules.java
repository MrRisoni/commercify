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
@Table(name = "shop_over_weight_ship_rules")
public class ShopWeightOverShipRules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

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

    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShippingZones zoneId;

    public ShopWeightOverShipRules() {
    }

    public ShopWeightOverShipRules(Long id) {
        this.id = id;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isTaxable() {
        return taxable;
    }

      public boolean isActive() {
        return active;
    }

    public ShippingZones getZoneId() {
        return zoneId;
    }

    public void setZoneId(ShippingZones zoneId) {
        this.zoneId = zoneId;
    }
}