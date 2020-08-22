
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "shop_tax_code_names")
public class ShopTaxCodeNames implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "code")
    private String code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxCodeId")
    private Collection<ShopTaxZipCodeRules> shopTaxZipCodeRulesCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxCodeId")
    private Collection<ShopTaxRegionRules> shopTaxRegionRulesCollection;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shops shopId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxCodeId")
    private Collection<ShopTaxRules> shopTaxRulesCollection;

    public ShopTaxCodeNames() {
    }

    public ShopTaxCodeNames(Long id) {
        this.id = id;
    }

    public ShopTaxCodeNames(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Collection<ShopTaxZipCodeRules> getShopTaxZipCodeRulesCollection() {
        return shopTaxZipCodeRulesCollection;
    }

    public void setShopTaxZipCodeRulesCollection(Collection<ShopTaxZipCodeRules> shopTaxZipCodeRulesCollection) {
        this.shopTaxZipCodeRulesCollection = shopTaxZipCodeRulesCollection;
    }

    public Collection<ShopTaxRegionRules> getShopTaxRegionRulesCollection() {
        return shopTaxRegionRulesCollection;
    }

    public void setShopTaxRegionRulesCollection(Collection<ShopTaxRegionRules> shopTaxRegionRulesCollection) {
        this.shopTaxRegionRulesCollection = shopTaxRegionRulesCollection;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }


    public Collection<ShopTaxRules> getShopTaxRulesCollection() {
        return shopTaxRulesCollection;
    }

    public void setShopTaxRulesCollection(Collection<ShopTaxRules> shopTaxRulesCollection) {
        this.shopTaxRulesCollection = shopTaxRulesCollection;
    }

}
