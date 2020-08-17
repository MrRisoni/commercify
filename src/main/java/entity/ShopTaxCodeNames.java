/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ekatania
 */
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taxCodeId")
    private Collection<ShopProductCateogoryTaxes> shopProductCateogoryTaxesCollection;
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

    @XmlTransient
    public Collection<ShopProductCateogoryTaxes> getShopProductCateogoryTaxesCollection() {
        return shopProductCateogoryTaxesCollection;
    }

    public void setShopProductCateogoryTaxesCollection(Collection<ShopProductCateogoryTaxes> shopProductCateogoryTaxesCollection) {
        this.shopProductCateogoryTaxesCollection = shopProductCateogoryTaxesCollection;
    }

    @XmlTransient
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

    @XmlTransient
    public Collection<ShopTaxRules> getShopTaxRulesCollection() {
        return shopTaxRulesCollection;
    }

    public void setShopTaxRulesCollection(Collection<ShopTaxRules> shopTaxRulesCollection) {
        this.shopTaxRulesCollection = shopTaxRulesCollection;
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
