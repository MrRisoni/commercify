/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ekatania
 */
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopDisableCodCountries> shopDisableCodCountriesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopManufacturers> shopManufacturersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopTaxRegionRules> shopTaxRegionRulesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopDisableZipCodes> shopDisableZipCodesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopWeightShipRules> shopWeightShipRulesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopShipZones> shopShipZonesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopManagers> shopManagersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopLanguages> shopLanguagesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopStyling> shopStylingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopCouriers> shopCouriersCollection;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users ownerId;
    @JoinColumn(name = "default_lang_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Languages defaultLangId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<Orders> ordersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopTaxCodeNames> shopTaxCodeNamesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopGiftwrap> shopGiftwrapCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopTranslations> shopTranslationsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopSuppliers> shopSuppliersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopReviews> shopReviewsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ProductReviews> productReviewsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<Products> productsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopTaxRules> shopTaxRulesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopDisableCodContinents> shopDisableCodContinentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ProductCategoryAttributes> productCategoryAttributesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopDisableCodZipcode> shopDisableCodZipcodeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopBelongsCategories> shopBelongsCategoriesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopEulas> shopEulasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ProductCategories> productCategoriesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopBanks> shopBanksCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopCurrencies> shopCurrenciesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopCountries> shopCountriesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopWeightCodRules> shopWeightCodRulesCollection;

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
    public Collection<ShopDisableCodCountries> getShopDisableCodCountriesCollection() {
        return shopDisableCodCountriesCollection;
    }

    public void setShopDisableCodCountriesCollection(Collection<ShopDisableCodCountries> shopDisableCodCountriesCollection) {
        this.shopDisableCodCountriesCollection = shopDisableCodCountriesCollection;
    }

    @XmlTransient
    public Collection<ShopManufacturers> getShopManufacturersCollection() {
        return shopManufacturersCollection;
    }

    public void setShopManufacturersCollection(Collection<ShopManufacturers> shopManufacturersCollection) {
        this.shopManufacturersCollection = shopManufacturersCollection;
    }

    @XmlTransient
    public Collection<ShopTaxRegionRules> getShopTaxRegionRulesCollection() {
        return shopTaxRegionRulesCollection;
    }

    public void setShopTaxRegionRulesCollection(Collection<ShopTaxRegionRules> shopTaxRegionRulesCollection) {
        this.shopTaxRegionRulesCollection = shopTaxRegionRulesCollection;
    }

    @XmlTransient
    public Collection<ShopDisableZipCodes> getShopDisableZipCodesCollection() {
        return shopDisableZipCodesCollection;
    }

    public void setShopDisableZipCodesCollection(Collection<ShopDisableZipCodes> shopDisableZipCodesCollection) {
        this.shopDisableZipCodesCollection = shopDisableZipCodesCollection;
    }

    @XmlTransient
    public Collection<ShopWeightShipRules> getShopWeightShipRulesCollection() {
        return shopWeightShipRulesCollection;
    }

    public void setShopWeightShipRulesCollection(Collection<ShopWeightShipRules> shopWeightShipRulesCollection) {
        this.shopWeightShipRulesCollection = shopWeightShipRulesCollection;
    }

    @XmlTransient
    public Collection<ShopShipZones> getShopShipZonesCollection() {
        return shopShipZonesCollection;
    }

    public void setShopShipZonesCollection(Collection<ShopShipZones> shopShipZonesCollection) {
        this.shopShipZonesCollection = shopShipZonesCollection;
    }

    @XmlTransient
    public Collection<ShopManagers> getShopManagersCollection() {
        return shopManagersCollection;
    }

    public void setShopManagersCollection(Collection<ShopManagers> shopManagersCollection) {
        this.shopManagersCollection = shopManagersCollection;
    }

    @XmlTransient
    public Collection<ShopLanguages> getShopLanguagesCollection() {
        return shopLanguagesCollection;
    }

    public void setShopLanguagesCollection(Collection<ShopLanguages> shopLanguagesCollection) {
        this.shopLanguagesCollection = shopLanguagesCollection;
    }

    @XmlTransient
    public Collection<ShopStyling> getShopStylingCollection() {
        return shopStylingCollection;
    }

    public void setShopStylingCollection(Collection<ShopStyling> shopStylingCollection) {
        this.shopStylingCollection = shopStylingCollection;
    }

    @XmlTransient
    public Collection<ShopCouriers> getShopCouriersCollection() {
        return shopCouriersCollection;
    }

    public void setShopCouriersCollection(Collection<ShopCouriers> shopCouriersCollection) {
        this.shopCouriersCollection = shopCouriersCollection;
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
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    public Collection<ShopTaxCodeNames> getShopTaxCodeNamesCollection() {
        return shopTaxCodeNamesCollection;
    }

    public void setShopTaxCodeNamesCollection(Collection<ShopTaxCodeNames> shopTaxCodeNamesCollection) {
        this.shopTaxCodeNamesCollection = shopTaxCodeNamesCollection;
    }

    @XmlTransient
    public Collection<ShopGiftwrap> getShopGiftwrapCollection() {
        return shopGiftwrapCollection;
    }

    public void setShopGiftwrapCollection(Collection<ShopGiftwrap> shopGiftwrapCollection) {
        this.shopGiftwrapCollection = shopGiftwrapCollection;
    }

    @XmlTransient
    public Collection<ShopTranslations> getShopTranslationsCollection() {
        return shopTranslationsCollection;
    }

    public void setShopTranslationsCollection(Collection<ShopTranslations> shopTranslationsCollection) {
        this.shopTranslationsCollection = shopTranslationsCollection;
    }

    @XmlTransient
    public Collection<ShopSuppliers> getShopSuppliersCollection() {
        return shopSuppliersCollection;
    }

    public void setShopSuppliersCollection(Collection<ShopSuppliers> shopSuppliersCollection) {
        this.shopSuppliersCollection = shopSuppliersCollection;
    }

    @XmlTransient
    public Collection<ShopReviews> getShopReviewsCollection() {
        return shopReviewsCollection;
    }

    public void setShopReviewsCollection(Collection<ShopReviews> shopReviewsCollection) {
        this.shopReviewsCollection = shopReviewsCollection;
    }

    @XmlTransient
    public Collection<ProductReviews> getProductReviewsCollection() {
        return productReviewsCollection;
    }

    public void setProductReviewsCollection(Collection<ProductReviews> productReviewsCollection) {
        this.productReviewsCollection = productReviewsCollection;
    }

    @XmlTransient
    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
    }

    @XmlTransient
    public Collection<ShopTaxRules> getShopTaxRulesCollection() {
        return shopTaxRulesCollection;
    }

    public void setShopTaxRulesCollection(Collection<ShopTaxRules> shopTaxRulesCollection) {
        this.shopTaxRulesCollection = shopTaxRulesCollection;
    }

    @XmlTransient
    public Collection<ShopDisableCodContinents> getShopDisableCodContinentsCollection() {
        return shopDisableCodContinentsCollection;
    }

    public void setShopDisableCodContinentsCollection(Collection<ShopDisableCodContinents> shopDisableCodContinentsCollection) {
        this.shopDisableCodContinentsCollection = shopDisableCodContinentsCollection;
    }

    @XmlTransient
    public Collection<ProductCategoryAttributes> getProductCategoryAttributesCollection() {
        return productCategoryAttributesCollection;
    }

    public void setProductCategoryAttributesCollection(Collection<ProductCategoryAttributes> productCategoryAttributesCollection) {
        this.productCategoryAttributesCollection = productCategoryAttributesCollection;
    }

    @XmlTransient
    public Collection<ShopDisableCodZipcode> getShopDisableCodZipcodeCollection() {
        return shopDisableCodZipcodeCollection;
    }

    public void setShopDisableCodZipcodeCollection(Collection<ShopDisableCodZipcode> shopDisableCodZipcodeCollection) {
        this.shopDisableCodZipcodeCollection = shopDisableCodZipcodeCollection;
    }

    @XmlTransient
    public Collection<ShopBelongsCategories> getShopBelongsCategoriesCollection() {
        return shopBelongsCategoriesCollection;
    }

    public void setShopBelongsCategoriesCollection(Collection<ShopBelongsCategories> shopBelongsCategoriesCollection) {
        this.shopBelongsCategoriesCollection = shopBelongsCategoriesCollection;
    }

    @XmlTransient
    public Collection<ShopEulas> getShopEulasCollection() {
        return shopEulasCollection;
    }

    public void setShopEulasCollection(Collection<ShopEulas> shopEulasCollection) {
        this.shopEulasCollection = shopEulasCollection;
    }

    @XmlTransient
    public Collection<ProductCategories> getProductCategoriesCollection() {
        return productCategoriesCollection;
    }

    public void setProductCategoriesCollection(Collection<ProductCategories> productCategoriesCollection) {
        this.productCategoriesCollection = productCategoriesCollection;
    }

    @XmlTransient
    public Collection<ShopBanks> getShopBanksCollection() {
        return shopBanksCollection;
    }

    public void setShopBanksCollection(Collection<ShopBanks> shopBanksCollection) {
        this.shopBanksCollection = shopBanksCollection;
    }

    @XmlTransient
    public Collection<ShopCurrencies> getShopCurrenciesCollection() {
        return shopCurrenciesCollection;
    }

    public void setShopCurrenciesCollection(Collection<ShopCurrencies> shopCurrenciesCollection) {
        this.shopCurrenciesCollection = shopCurrenciesCollection;
    }

    @XmlTransient
    public Collection<ShopCountries> getShopCountriesCollection() {
        return shopCountriesCollection;
    }

    public void setShopCountriesCollection(Collection<ShopCountries> shopCountriesCollection) {
        this.shopCountriesCollection = shopCountriesCollection;
    }

    @XmlTransient
    public Collection<ShopWeightCodRules> getShopWeightCodRulesCollection() {
        return shopWeightCodRulesCollection;
    }

    public void setShopWeightCodRulesCollection(Collection<ShopWeightCodRules> shopWeightCodRulesCollection) {
        this.shopWeightCodRulesCollection = shopWeightCodRulesCollection;
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
