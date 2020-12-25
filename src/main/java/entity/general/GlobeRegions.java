package entity.general;

import entity.shipping.ShippingZonesRegions;
import entity.taxes.ShopTaxRegionRules;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "globe_regions")
public class GlobeRegions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "country_code")
    private String countryCode;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId")
    private Collection<ShopTaxRegionRules> shopTaxRegionRulesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regionId")
    private Collection<ShippingZonesRegions> shippingZonesRegionsCollection;

    public GlobeRegions() {
    }
}