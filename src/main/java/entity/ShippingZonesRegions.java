
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



@Entity
@Table(name = "shipping_zones_regions")
@NamedQueries({
    @NamedQuery(name = "ShippingZonesRegions.findAll", query = "SELECT s FROM ShippingZonesRegions s")})
public class ShippingZonesRegions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GlobeRegions regionId;
    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ShopShipZones zoneId;

    public ShippingZonesRegions() {
    }

    public ShippingZonesRegions(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GlobeRegions getRegionId() {
        return regionId;
    }

    public void setRegionId(GlobeRegions regionId) {
        this.regionId = regionId;
    }

    public ShopShipZones getZoneId() {
        return zoneId;
    }

    public void setZoneId(ShopShipZones zoneId) {
        this.zoneId = zoneId;
    }



    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShippingZonesRegions)) {
            return false;
        }
        ShippingZonesRegions other = (ShippingZonesRegions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShippingZonesRegions[ id=" + id + " ]";
    }
    
}
