
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "shop_shipping_classes_regions")
public class ShopShippingClassesRegions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;

    @JoinColumn(name = "ship_class_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopCourierClasses shipClassId;

    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GlobeRegions regionId;

    public ShopShippingClassesRegions() {
    }

    public ShopShippingClassesRegions(Long id) {
        this.id = id;
    }

    public ShopShippingClassesRegions(Long id, boolean active) {
        this.id = id;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ShopCourierClasses getShipClassId() {
        return shipClassId;
    }

    public void setShipClassId(ShopCourierClasses shipClassId) {
        this.shipClassId = shipClassId;
    }

    public GlobeRegions getRegionId() {
        return regionId;
    }

    public void setRegionId(GlobeRegions regionId) {
        this.regionId = regionId;
    }
}