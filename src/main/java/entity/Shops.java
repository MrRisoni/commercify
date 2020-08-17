
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@Entity
@Table(name = "shops")
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


    public Collection<ShopDisableCodCountries> getShopDisableCodCountriesCollection() {
        return shopDisableCodCountriesCollection;
    }

    public void setShopDisableCodCountriesCollection(Collection<ShopDisableCodCountries> shopDisableCodCountriesCollection) {
        this.shopDisableCodCountriesCollection = shopDisableCodCountriesCollection;
    }


    public Collection<ShopManufacturers> getShopManufacturersCollection() {
        return shopManufacturersCollection;
    }

    public void setShopManufacturersCollection(Collection<ShopManufacturers> shopManufacturersCollection) {
        this.shopManufacturersCollection = shopManufacturersCollection;
    }


    public Collection<ShopTaxRegionRules> getShopTaxRegionRulesCollection() {
        return shopTaxRegionRulesCollection;
    }

    public void setShopTaxRegionRulesCollection(Collection<ShopTaxRegionRules> shopTaxRegionRulesCollection) {
        this.shopTaxRegionRulesCollection = shopTaxRegionRulesCollection;
    }


    public Collection<ShopDisableZipCodes> getShopDisableZipCodesCollection() {
        return shopDisableZipCodesCollection;
    }

    public void setShopDisableZipCodesCollection(Collection<ShopDisableZipCodes> shopDisableZipCodesCollection) {
        this.shopDisableZipCodesCollection = shopDisableZipCodesCollection;
    }


    public Collection<ShopWeightShipRules> getShopWeightShipRulesCollection() {
        return shopWeightShipRulesCollection;
    }

    public void setShopWeightShipRulesCollection(Collection<ShopWeightShipRules> shopWeightShipRulesCollection) {
        this.shopWeightShipRulesCollection = shopWeightShipRulesCollection;
    }


    public Collection<ShopShipZones> getShopShipZonesCollection() {
        return shopShipZonesCollection;
    }

    public void setShopShipZonesCollection(Collection<ShopShipZones> shopShipZonesCollection) {
        this.shopShipZonesCollection = shopShipZonesCollection;
    }


    public Collection<ShopManagers> getShopManagersCollection() {
        return shopManagersCollection;
    }

    public void setShopManagersCollection(Collection<ShopManagers> shopManagersCollection) {
        this.shopManagersCollection = shopManagersCollection;
    }


    public Collection<ShopLanguages> getShopLanguagesCollection() {
        return shopLanguagesCollection;
    }

    public void setShopLanguagesCollection(Collection<ShopLanguages> shopLanguagesCollection) {
        this.shopLanguagesCollection = shopLanguagesCollection;
    }


    public Collection<ShopStyling> getShopStylingCollection() {
        return shopStylingCollection;
    }

    public void setShopStylingCollection(Collection<ShopStyling> shopStylingCollection) {
        this.shopStylingCollection = shopStylingCollection;
    }


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


    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }


    public Collection<ShopTaxCodeNames> getShopTaxCodeNamesCollection() {
        return shopTaxCodeNamesCollection;
    }

    public void setShopTaxCodeNamesCollection(Collection<ShopTaxCodeNames> shopTaxCodeNamesCollection) {
        this.shopTaxCodeNamesCollection = shopTaxCodeNamesCollection;
    }


    public Collection<ShopGiftwrap> getShopGiftwrapCollection() {
        return shopGiftwrapCollection;
    }

    public void setShopGiftwrapCollection(Collection<ShopGiftwrap> shopGiftwrapCollection) {
        this.shopGiftwrapCollection = shopGiftwrapCollection;
    }


    public Collection<ShopTranslations> getShopTranslationsCollection() {
        return shopTranslationsCollection;
    }

    public void setShopTranslationsCollection(Collection<ShopTranslations> shopTranslationsCollection) {
        this.shopTranslationsCollection = shopTranslationsCollection;
    }


    public Collection<ShopSuppliers> getShopSuppliersCollection() {
        return shopSuppliersCollection;
    }

    public void setShopSuppliersCollection(Collection<ShopSuppliers> shopSuppliersCollection) {
        this.shopSuppliersCollection = shopSuppliersCollection;
    }


    public Collection<ShopReviews> getShopReviewsCollection() {
        return shopReviewsCollection;
    }

    public void setShopReviewsCollection(Collection<ShopReviews> shopReviewsCollection) {
        this.shopReviewsCollection = shopReviewsCollection;
    }


    public Collection<ProductReviews> getProductReviewsCollection() {
        return productReviewsCollection;
    }

    public void setProductReviewsCollection(Collection<ProductReviews> productReviewsCollection) {
        this.productReviewsCollection = productReviewsCollection;
    }


    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
    }


    public Collection<ShopTaxRules> getShopTaxRulesCollection() {
        return shopTaxRulesCollection;
    }

    public void setShopTaxRulesCollection(Collection<ShopTaxRules> shopTaxRulesCollection) {
        this.shopTaxRulesCollection = shopTaxRulesCollection;
    }


    public Collection<ShopDisableCodContinents> getShopDisableCodContinentsCollection() {
        return shopDisableCodContinentsCollection;
    }

    public void setShopDisableCodContinentsCollection(Collection<ShopDisableCodContinents> shopDisableCodContinentsCollection) {
        this.shopDisableCodContinentsCollection = shopDisableCodContinentsCollection;
    }


    public Collection<ProductCategoryAttributes> getProductCategoryAttributesCollection() {
        return productCategoryAttributesCollection;
    }

    public void setProductCategoryAttributesCollection(Collection<ProductCategoryAttributes> productCategoryAttributesCollection) {
        this.productCategoryAttributesCollection = productCategoryAttributesCollection;
    }


    public Collection<ShopDisableCodZipcode> getShopDisableCodZipcodeCollection() {
        return shopDisableCodZipcodeCollection;
    }

    public void setShopDisableCodZipcodeCollection(Collection<ShopDisableCodZipcode> shopDisableCodZipcodeCollection) {
        this.shopDisableCodZipcodeCollection = shopDisableCodZipcodeCollection;
    }


    public Collection<ShopBelongsCategories> getShopBelongsCategoriesCollection() {
        return shopBelongsCategoriesCollection;
    }

    public void setShopBelongsCategoriesCollection(Collection<ShopBelongsCategories> shopBelongsCategoriesCollection) {
        this.shopBelongsCategoriesCollection = shopBelongsCategoriesCollection;
    }


    public Collection<ShopEulas> getShopEulasCollection() {
        return shopEulasCollection;
    }

    public void setShopEulasCollection(Collection<ShopEulas> shopEulasCollection) {
        this.shopEulasCollection = shopEulasCollection;
    }


    public Collection<ProductCategories> getProductCategoriesCollection() {
        return productCategoriesCollection;
    }

    public void setProductCategoriesCollection(Collection<ProductCategories> productCategoriesCollection) {
        this.productCategoriesCollection = productCategoriesCollection;
    }


    public Collection<ShopBanks> getShopBanksCollection() {
        return shopBanksCollection;
    }

    public void setShopBanksCollection(Collection<ShopBanks> shopBanksCollection) {
        this.shopBanksCollection = shopBanksCollection;
    }


    public Collection<ShopCurrencies> getShopCurrenciesCollection() {
        return shopCurrenciesCollection;
    }

    public void setShopCurrenciesCollection(Collection<ShopCurrencies> shopCurrenciesCollection) {
        this.shopCurrenciesCollection = shopCurrenciesCollection;
    }


    public Collection<ShopCountries> getShopCountriesCollection() {
        return shopCountriesCollection;
    }

    public void setShopCountriesCollection(Collection<ShopCountries> shopCountriesCollection) {
        this.shopCountriesCollection = shopCountriesCollection;
    }


    public Collection<ShopWeightCodRules> getShopWeightCodRulesCollection() {
        return shopWeightCodRulesCollection;
    }

    public void setShopWeightCodRulesCollection(Collection<ShopWeightCodRules> shopWeightCodRulesCollection) {
        this.shopWeightCodRulesCollection = shopWeightCodRulesCollection;
    }

}
