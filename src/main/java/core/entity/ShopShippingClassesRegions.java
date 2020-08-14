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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ekatania
 */
@Entity
@Table(name = "shop_shipping_classes_regions")
@XmlRootElement
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopCourierClasses shopCourierClasses;
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GlobeRegions globeRegions;

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

    public ShopCourierClasses getShopCourierClasses() {
        return shopCourierClasses;
    }

    public void setShopCourierClasses(ShopCourierClasses shopCourierClasses) {
        this.shopCourierClasses = shopCourierClasses;
    }

    public GlobeRegions getGlobeRegions() {
        return globeRegions;
    }

    public void setGlobeRegions(GlobeRegions globeRegions) {
        this.globeRegions = globeRegions;
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
        return "core.entity.ShopShippingClassesRegions[ id=" + id + " ]";
    }
    
}
