
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "globe_regions")
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


    public Collection<ShopTaxRegionRules> getShopTaxRegionRulesCollection() {
        return shopTaxRegionRulesCollection;
    }

    public void setShopTaxRegionRulesCollection(Collection<ShopTaxRegionRules> shopTaxRegionRulesCollection) {
        this.shopTaxRegionRulesCollection = shopTaxRegionRulesCollection;
    }


    public Collection<ShippingZonesRegions> getShippingZonesRegionsCollection() {
        return shippingZonesRegionsCollection;
    }

    public void setShippingZonesRegionsCollection(Collection<ShippingZonesRegions> shippingZonesRegionsCollection) {
        this.shippingZonesRegionsCollection = shippingZonesRegionsCollection;
    }



}
