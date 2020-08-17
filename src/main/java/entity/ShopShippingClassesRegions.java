
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "shop_shipping_classes_regions")
@NamedQueries({
    @NamedQuery(name = "ShopShippingClassesRegions.findAll", query = "SELECT s FROM ShopShippingClassesRegions s")})
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
    @ManyToOne(optional = false)
    private ShopCourierClasses shipClassId;
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
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



    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShopShippingClassesRegions)) {
            return false;
        }
        ShopShippingClassesRegions other = (ShopShippingClassesRegions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShopShippingClassesRegions[ id=" + id + " ]";
    }
    
}
