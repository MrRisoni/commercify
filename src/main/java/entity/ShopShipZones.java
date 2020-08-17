
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@Entity
@Table(name = "shop_ship_zones")
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zoneId")
    private Collection<ShippingZonesRegions> shippingZonesRegionsCollection;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shops shopId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zoneId")
    private Collection<ShippingZonesZipCodes> shippingZonesZipCodesCollection;

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


    public Collection<ShippingZonesZipCodes> getShippingZonesZipCodesCollection() {
        return shippingZonesZipCodesCollection;
    }

    public void setShippingZonesZipCodesCollection(Collection<ShippingZonesZipCodes> shippingZonesZipCodesCollection) {
        this.shippingZonesZipCodesCollection = shippingZonesZipCodesCollection;
    }
}
