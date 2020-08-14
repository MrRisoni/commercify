
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "shops")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shops.findAll", query = "SELECT s FROM Shops s")})
public class Shops implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopDisableCodCountries> shopDisableCodCountriesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopTaxRegionRules> shopTaxRegionRulesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopDisableZipCodes> shopDisableZipCodesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopWeightShipRules> shopWeightShipRulesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopShipZones> shopShipZonesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopManagers> shopManagersSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopLanguages> shopLanguagesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopStyling> shopStylingSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopCouriers> shopCouriersSet;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users ownerId;
    @JoinColumn(name = "default_lang_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Languages defaultLangId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<Orders> ordersSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopTaxCodeNames> shopTaxCodeNamesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopGiftwrap> shopGiftwrapSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopReviews> shopReviewsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ProductReviews> productReviewsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<Products> productsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopTaxRules> shopTaxRulesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopDisableCodContinents> shopDisableCodContinentsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopDisableCodZipcode> shopDisableCodZipcodeSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopBelongsCategories> shopBelongsCategoriesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopBanks> shopBanksSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopCurrencies> shopCurrenciesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopCountries> shopCountriesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId", fetch = FetchType.LAZY)
    private Set<ShopWeightCodRules> shopWeightCodRulesSet;

    public Shops() {
    }

    public Shops(Long id) {
        this.id = id;
    }

    public Shops(Long id, String title, Date created) {
        this.id = id;
        this.title = title;
        this.created = created;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @XmlTransient
    public Set<ShopDisableCodCountries> getShopDisableCodCountriesSet() {
        return shopDisableCodCountriesSet;
    }

    public void setShopDisableCodCountriesSet(Set<ShopDisableCodCountries> shopDisableCodCountriesSet) {
        this.shopDisableCodCountriesSet = shopDisableCodCountriesSet;
    }

    @XmlTransient
    public Set<ShopTaxRegionRules> getShopTaxRegionRulesSet() {
        return shopTaxRegionRulesSet;
    }

    public void setShopTaxRegionRulesSet(Set<ShopTaxRegionRules> shopTaxRegionRulesSet) {
        this.shopTaxRegionRulesSet = shopTaxRegionRulesSet;
    }

    @XmlTransient
    public Set<ShopDisableZipCodes> getShopDisableZipCodesSet() {
        return shopDisableZipCodesSet;
    }

    public void setShopDisableZipCodesSet(Set<ShopDisableZipCodes> shopDisableZipCodesSet) {
        this.shopDisableZipCodesSet = shopDisableZipCodesSet;
    }

    @XmlTransient
    public Set<ShopWeightShipRules> getShopWeightShipRulesSet() {
        return shopWeightShipRulesSet;
    }

    public void setShopWeightShipRulesSet(Set<ShopWeightShipRules> shopWeightShipRulesSet) {
        this.shopWeightShipRulesSet = shopWeightShipRulesSet;
    }

    @XmlTransient
    public Set<ShopShipZones> getShopShipZonesSet() {
        return shopShipZonesSet;
    }

    public void setShopShipZonesSet(Set<ShopShipZones> shopShipZonesSet) {
        this.shopShipZonesSet = shopShipZonesSet;
    }

    @XmlTransient
    public Set<ShopManagers> getShopManagersSet() {
        return shopManagersSet;
    }

    public void setShopManagersSet(Set<ShopManagers> shopManagersSet) {
        this.shopManagersSet = shopManagersSet;
    }

    @XmlTransient
    public Set<ShopLanguages> getShopLanguagesSet() {
        return shopLanguagesSet;
    }

    public void setShopLanguagesSet(Set<ShopLanguages> shopLanguagesSet) {
        this.shopLanguagesSet = shopLanguagesSet;
    }

    @XmlTransient
    public Set<ShopStyling> getShopStylingSet() {
        return shopStylingSet;
    }

    public void setShopStylingSet(Set<ShopStyling> shopStylingSet) {
        this.shopStylingSet = shopStylingSet;
    }

    @XmlTransient
    public Set<ShopCouriers> getShopCouriersSet() {
        return shopCouriersSet;
    }

    public void setShopCouriersSet(Set<ShopCouriers> shopCouriersSet) {
        this.shopCouriersSet = shopCouriersSet;
    }

    public Users getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Users ownerId) {
        this.ownerId = ownerId;
    }

    public Languages getDefaultLangId() {
        return defaultLangId;
    }

    public void setDefaultLangId(Languages defaultLangId) {
        this.defaultLangId = defaultLangId;
    }

    @XmlTransient
    public Set<Orders> getOrdersSet() {
        return ordersSet;
    }

    public void setOrdersSet(Set<Orders> ordersSet) {
        this.ordersSet = ordersSet;
    }

    @XmlTransient
    public Set<ShopTaxCodeNames> getShopTaxCodeNamesSet() {
        return shopTaxCodeNamesSet;
    }

    public void setShopTaxCodeNamesSet(Set<ShopTaxCodeNames> shopTaxCodeNamesSet) {
        this.shopTaxCodeNamesSet = shopTaxCodeNamesSet;
    }

    @XmlTransient
    public Set<ShopGiftwrap> getShopGiftwrapSet() {
        return shopGiftwrapSet;
    }

    public void setShopGiftwrapSet(Set<ShopGiftwrap> shopGiftwrapSet) {
        this.shopGiftwrapSet = shopGiftwrapSet;
    }

    @XmlTransient
    public Set<ShopReviews> getShopReviewsSet() {
        return shopReviewsSet;
    }

    public void setShopReviewsSet(Set<ShopReviews> shopReviewsSet) {
        this.shopReviewsSet = shopReviewsSet;
    }

    @XmlTransient
    public Set<ProductReviews> getProductReviewsSet() {
        return productReviewsSet;
    }

    public void setProductReviewsSet(Set<ProductReviews> productReviewsSet) {
        this.productReviewsSet = productReviewsSet;
    }

    @XmlTransient
    public Set<Products> getProductsSet() {
        return productsSet;
    }

    public void setProductsSet(Set<Products> productsSet) {
        this.productsSet = productsSet;
    }

    @XmlTransient
    public Set<ShopTaxRules> getShopTaxRulesSet() {
        return shopTaxRulesSet;
    }

    public void setShopTaxRulesSet(Set<ShopTaxRules> shopTaxRulesSet) {
        this.shopTaxRulesSet = shopTaxRulesSet;
    }

    @XmlTransient
    public Set<ShopDisableCodContinents> getShopDisableCodContinentsSet() {
        return shopDisableCodContinentsSet;
    }

    public void setShopDisableCodContinentsSet(Set<ShopDisableCodContinents> shopDisableCodContinentsSet) {
        this.shopDisableCodContinentsSet = shopDisableCodContinentsSet;
    }

    @XmlTransient
    public Set<ShopDisableCodZipcode> getShopDisableCodZipcodeSet() {
        return shopDisableCodZipcodeSet;
    }

    public void setShopDisableCodZipcodeSet(Set<ShopDisableCodZipcode> shopDisableCodZipcodeSet) {
        this.shopDisableCodZipcodeSet = shopDisableCodZipcodeSet;
    }

    @XmlTransient
    public Set<ShopBelongsCategories> getShopBelongsCategoriesSet() {
        return shopBelongsCategoriesSet;
    }

    public void setShopBelongsCategoriesSet(Set<ShopBelongsCategories> shopBelongsCategoriesSet) {
        this.shopBelongsCategoriesSet = shopBelongsCategoriesSet;
    }

    @XmlTransient
    public Set<ShopBanks> getShopBanksSet() {
        return shopBanksSet;
    }

    public void setShopBanksSet(Set<ShopBanks> shopBanksSet) {
        this.shopBanksSet = shopBanksSet;
    }

    @XmlTransient
    public Set<ShopCurrencies> getShopCurrenciesSet() {
        return shopCurrenciesSet;
    }

    public void setShopCurrenciesSet(Set<ShopCurrencies> shopCurrenciesSet) {
        this.shopCurrenciesSet = shopCurrenciesSet;
    }

    @XmlTransient
    public Set<ShopCountries> getShopCountriesSet() {
        return shopCountriesSet;
    }

    public void setShopCountriesSet(Set<ShopCountries> shopCountriesSet) {
        this.shopCountriesSet = shopCountriesSet;
    }

    @XmlTransient
    public Set<ShopWeightCodRules> getShopWeightCodRulesSet() {
        return shopWeightCodRulesSet;
    }

    public void setShopWeightCodRulesSet(Set<ShopWeightCodRules> shopWeightCodRulesSet) {
        this.shopWeightCodRulesSet = shopWeightCodRulesSet;
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
        if (!(object instanceof Shops)) {
            return false;
        }
        Shops other = (Shops) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Shops[ id=" + id + " ]";
    }
    
}
