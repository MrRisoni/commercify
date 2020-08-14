/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ekatania
 */
@Entity
@Table(name = "shop_ship_zones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopShipZones.findAll", query = "SELECT s FROM ShopShipZones s")})
public class ShopShipZones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "title")
    private String title;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "ship_cost")
    private BigDecimal shipCost;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zoneId", fetch = FetchType.LAZY)
    private Set<ShippingZonesRegions> shippingZonesRegionsSet;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zoneId", fetch = FetchType.LAZY)
    private Set<ShippingZonesZipCodes> shippingZonesZipCodesSet;

    public ShopShipZones() {
    }

    public ShopShipZones(Long id) {
        this.id = id;
    }

    public ShopShipZones(Long id, String title, BigDecimal shipCost) {
        this.id = id;
        this.title = title;
        this.shipCost = shipCost;
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

    public BigDecimal getShipCost() {
        return shipCost;
    }

    public void setShipCost(BigDecimal shipCost) {
        this.shipCost = shipCost;
    }

    @XmlTransient
    public Set<ShippingZonesRegions> getShippingZonesRegionsSet() {
        return shippingZonesRegionsSet;
    }

    public void setShippingZonesRegionsSet(Set<ShippingZonesRegions> shippingZonesRegionsSet) {
        this.shippingZonesRegionsSet = shippingZonesRegionsSet;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    @XmlTransient
    public Set<ShippingZonesZipCodes> getShippingZonesZipCodesSet() {
        return shippingZonesZipCodesSet;
    }

    public void setShippingZonesZipCodesSet(Set<ShippingZonesZipCodes> shippingZonesZipCodesSet) {
        this.shippingZonesZipCodesSet = shippingZonesZipCodesSet;
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
        if (!(object instanceof ShopShipZones)) {
            return false;
        }
        ShopShipZones other = (ShopShipZones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShopShipZones[ id=" + id + " ]";
    }
    
}
