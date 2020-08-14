
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "shop_tax_code_names")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopTaxCodeNames.findAll", query = "SELECT s FROM ShopTaxCodeNames s")})
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxCodeId", fetch = FetchType.LAZY)
    private Set<ShopProductCateogoryTaxes> shopProductCateogoryTaxesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxCodeId", fetch = FetchType.LAZY)
    private Set<ShopTaxRegionRules> shopTaxRegionRulesSet;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxCodeId", fetch = FetchType.LAZY)
    private Set<ShopTaxRules> shopTaxRulesSet;

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

    @XmlTransient
    public Set<ShopProductCateogoryTaxes> getShopProductCateogoryTaxesSet() {
        return shopProductCateogoryTaxesSet;
    }

    public void setShopProductCateogoryTaxesSet(Set<ShopProductCateogoryTaxes> shopProductCateogoryTaxesSet) {
        this.shopProductCateogoryTaxesSet = shopProductCateogoryTaxesSet;
    }

    @XmlTransient
    public Set<ShopTaxRegionRules> getShopTaxRegionRulesSet() {
        return shopTaxRegionRulesSet;
    }

    public void setShopTaxRegionRulesSet(Set<ShopTaxRegionRules> shopTaxRegionRulesSet) {
        this.shopTaxRegionRulesSet = shopTaxRegionRulesSet;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
    }

    @XmlTransient
    public Set<ShopTaxRules> getShopTaxRulesSet() {
        return shopTaxRulesSet;
    }

    public void setShopTaxRulesSet(Set<ShopTaxRules> shopTaxRulesSet) {
        this.shopTaxRulesSet = shopTaxRulesSet;
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
        if (!(object instanceof ShopTaxCodeNames)) {
            return false;
        }
        ShopTaxCodeNames other = (ShopTaxCodeNames) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ShopTaxCodeNames[ id=" + id + " ]";
    }
    
}
