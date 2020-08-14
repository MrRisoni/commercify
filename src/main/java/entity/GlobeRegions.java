
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId", fetch = FetchType.LAZY)
    private Set<ShopTaxRegionRules> shopTaxRegionRulesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId", fetch = FetchType.LAZY)
    private Set<ShippingZonesRegions> shippingZonesRegionsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId", fetch = FetchType.LAZY)
    private Set<ShopShippingClassesRegions> shopShippingClassesRegionsSet;

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
    public Set<ShopTaxRegionRules> getShopTaxRegionRulesSet() {
        return shopTaxRegionRulesSet;
    }

    public void setShopTaxRegionRulesSet(Set<ShopTaxRegionRules> shopTaxRegionRulesSet) {
        this.shopTaxRegionRulesSet = shopTaxRegionRulesSet;
    }

    @XmlTransient
    public Set<ShippingZonesRegions> getShippingZonesRegionsSet() {
        return shippingZonesRegionsSet;
    }

    public void setShippingZonesRegionsSet(Set<ShippingZonesRegions> shippingZonesRegionsSet) {
        this.shippingZonesRegionsSet = shippingZonesRegionsSet;
    }

    @XmlTransient
    public Set<ShopShippingClassesRegions> getShopShippingClassesRegionsSet() {
        return shopShippingClassesRegionsSet;
    }

    public void setShopShippingClassesRegionsSet(Set<ShopShippingClassesRegions> shopShippingClassesRegionsSet) {
        this.shopShippingClassesRegionsSet = shopShippingClassesRegionsSet;
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
