package entity.shipping;

import entity.shop.Shops;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "shop_over_weight_ship_rules")
public class ShopWeightOverShipRules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column
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

}