/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ekatania
 */
@Entity
@Table(name = "shipping_zones_regions")
@XmlRootElement
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GlobeRegions globeRegions;
    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopShipZones shopShipZones;

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

    public GlobeRegions getGlobeRegions() {
        return globeRegions;
    }

    public void setGlobeRegions(GlobeRegions globeRegions) {
        this.globeRegions = globeRegions;
    }

    public ShopShipZones getShopShipZones() {
        return shopShipZones;
    }

    public void setShopShipZones(ShopShipZones shopShipZones) {
        this.shopShipZones = shopShipZones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
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
        return "core.entity.ShippingZonesRegions[ id=" + id + " ]";
    }
    
}
