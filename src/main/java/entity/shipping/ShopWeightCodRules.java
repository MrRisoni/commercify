package entity.shipping;

import entity.shop.Shops;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shop_weight_cod_rules")
public class ShopWeightCodRules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column
    private String title;

    @Basic(optional = false)
    @NotNull
    @Column
    private boolean taxable;

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
    @Column
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
    @Column
    private boolean active;

    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @JoinColumn(name = "shipping_class_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopCourierClasses shippingClassId;

    public ShopWeightCodRules() {
    }

    public ShopWeightCodRules(Long id) {
        this.id = id;
    }

    public ShopWeightCodRules(Long id, String title, boolean taxable, BigDecimal lessThanKg, boolean lessEqual, BigDecimal overThanKg, boolean overEqual, BigDecimal baseCost, BigDecimal charge, BigDecimal overTotalWeight, BigDecimal forEachKg, boolean active, Date createdAt) {
        this.id = id;
        this.title = title;
        this.taxable = taxable;
        this.lessThanKg = lessThanKg;
        this.lessEqual = lessEqual;
        this.overThanKg = overThanKg;
        this.overEqual = overEqual;
        this.baseCost = baseCost;
        this.charge = charge;
        this.overTotalWeight = overTotalWeight;
        this.forEachKg = forEachKg;
        this.active = active;
        this.createdAt = createdAt;
    }
}