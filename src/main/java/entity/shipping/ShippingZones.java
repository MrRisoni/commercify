
package entity.shipping;

import entity.shop.Shops;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "shipping_zones")
public class ShippingZones implements Serializable {

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zoneId")
    private Collection<ShippingZonesRegions> shippingZonesRegionsCollection;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zoneId")
    private Collection<ShopWeightShipRules> shipWeightRulesCollection;

    public ShippingZones() {
    }

    public ShippingZones(Long id) {
        this.id = id;
    }

    public ShippingZones(Long id, String title) {
        this.id = id;
        this.title = title;
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


    public Collection<ShippingZonesRegions> getShippingZonesRegionsCollection() {
        return shippingZonesRegionsCollection;
    }

    public void setShippingZonesRegionsCollection(Collection<ShippingZonesRegions> shippingZonesRegionsCollection) {
        this.shippingZonesRegionsCollection = shippingZonesRegionsCollection;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    public Collection<ShopWeightShipRules> getShipWeightRulesCollection() {
        return shipWeightRulesCollection;
    }

    public void setShipWeightRulesCollection(Collection<ShopWeightShipRules> shipWeightRulesCollection) {
        this.shipWeightRulesCollection = shipWeightRulesCollection;
    }
}
