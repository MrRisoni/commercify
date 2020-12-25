package entity.shop;

import entity.general.Languages;
import entity.order.Orders;
import entity.product.ProductCategories;
import entity.product.ProductCategoryAttributes;
import entity.product.ProductReviews;
import entity.product.Products;
import entity.rules.RestrictPaymentRules;
import entity.shipping.*;
import entity.taxes.ShopTaxRegionRules;
import entity.taxes.ShopTaxRules;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shops")
public class Shops implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
     @Column
    private String title;

    @Basic(optional = false)
    @NotNull
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopManufacturers> shopManufacturersCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopTaxRegionRules> shopTaxRegionRulesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopDisableZipCodes> shopDisableZipCodesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopWeightShipRules> shopWeightShipRulesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShippingZones> shopShipZonesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopManagers> shopManagersCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopLanguages> shopLanguagesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopStyling> shopStylingCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<ShopCouriers> shopCouriersCollection;

    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users ownerId;

    @JoinColumn(name = "default_lang_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Languages defaultLangId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<Orders> ordersCollection;

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
    private Collection<ProductCategoryAttributes> productCategoryAttributesCollection;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopId")
    private Collection<RestrictPaymentRules> restrictPaymentRulesCollection;

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
}