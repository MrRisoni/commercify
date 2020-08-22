
package entity;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "shop_tax_region_rules")
public class ShopTaxRegionRules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column(name = "title")
    private String title;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "country_code")
    private String countryCode;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "tax_address")
    private String taxAddress;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "flat_cost")
    private BigDecimal flatCost;

    @Basic(optional = false)
    @NotNull
    @Column(name = "rate")
    private BigDecimal rate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shops shopId;

    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GlobeRegions regionId;

    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductCategories productCategoryId;

    public ShopTaxRegionRules() {
    }

    public ShopTaxRegionRules(Long id) {
        this.id = id;
    }

    public ShopTaxRegionRules(Long id, String title, String countryCode, BigDecimal flatCost, BigDecimal rate, Date created, boolean active) {
        this.id = id;
        this.title = title;
        this.countryCode = countryCode;
        this.flatCost = flatCost;
        this.rate = rate;
        this.created = created;
        this.active = active;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public BigDecimal getFlatCost() {
        return flatCost;
    }

    public void setFlatCost(BigDecimal flatCost) {
        this.flatCost = flatCost;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    public GlobeRegions getRegionId() {
        return regionId;
    }

    public void setRegionId(GlobeRegions regionId) {
        this.regionId = regionId;
    }


    public String getTaxAddress() {
        return taxAddress;
    }

    public void setTaxAddress(String taxAddress) {
        this.taxAddress = taxAddress;
    }

    public boolean isActive() {
        return active;
    }

    public ProductCategories getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(ProductCategories productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
}
