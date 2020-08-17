/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "globe_regions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlobeRegions.findAll", query = "SELECT g FROM GlobeRegions g")})
public class GlobeRegions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "country_code")
    private String countryCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "title")
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId")
    private Collection<ShopTaxRegionRules> shopTaxRegionRulesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId")
    private Collection<ShippingZonesRegions> shippingZonesRegionsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId")
    private Collection<ShopShippingClassesRegions> shopShippingClassesRegionsCollection;

    public GlobeRegions() {
    }

    public GlobeRegions(Long id) {
        this.id = id;
    }

    public GlobeRegions(Long id, String countryCode, String title) {
        this.id = id;
        this.countryCode = countryCode;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public Collection<ShopTaxRegionRules> getShopTaxRegionRulesCollection() {
        return shopTaxRegionRulesCollection;
    }

    public void setShopTaxRegionRulesCollection(Collection<ShopTaxRegionRules> shopTaxRegionRulesCollection) {
        this.shopTaxRegionRulesCollection = shopTaxRegionRulesCollection;
    }

    @XmlTransient
    public Collection<ShippingZonesRegions> getShippingZonesRegionsCollection() {
        return shippingZonesRegionsCollection;
    }

    public void setShippingZonesRegionsCollection(Collection<ShippingZonesRegions> shippingZonesRegionsCollection) {
        this.shippingZonesRegionsCollection = shippingZonesRegionsCollection;
    }

    @XmlTransient
    public Collection<ShopShippingClassesRegions> getShopShippingClassesRegionsCollection() {
        return shopShippingClassesRegionsCollection;
    }

    public void setShopShippingClassesRegionsCollection(Collection<ShopShippingClassesRegions> shopShippingClassesRegionsCollection) {
        this.shopShippingClassesRegionsCollection = shopShippingClassesRegionsCollection;
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
        if (!(object instanceof GlobeRegions)) {
            return false;
        }
        GlobeRegions other = (GlobeRegions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GlobeRegions[ id=" + id + " ]";
    }
    
}
