/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ekatania
 */
@Entity
@Table(name = "shop_product_cateogory_taxes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopProductCateogoryTaxes.findAll", query = "SELECT s FROM ShopProductCateogoryTaxes s")})
public class ShopProductCateogoryTaxes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "shop_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopBelongsCategories shopBelongsCategories;
    @JoinColumn(name = "tax_code_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopTaxCodeNames shopTaxCodeNames;

    public ShopProductCateogoryTaxes() {
    }

    public ShopProductCateogoryTaxes(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShopBelongsCategories getShopBelongsCategories() {
        return shopBelongsCategories;
    }

    public void setShopBelongsCategories(ShopBelongsCategories shopBelongsCategories) {
        this.shopBelongsCategories = shopBelongsCategories;
    }

    public ShopTaxCodeNames getShopTaxCodeNames() {
        return shopTaxCodeNames;
    }

    public void setShopTaxCodeNames(ShopTaxCodeNames shopTaxCodeNames) {
        this.shopTaxCodeNames = shopTaxCodeNames;
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
        if (!(object instanceof ShopProductCateogoryTaxes)) {
            return false;
        }
        ShopProductCateogoryTaxes other = (ShopProductCateogoryTaxes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "core.entity.ShopProductCateogoryTaxes[ id=" + id + " ]";
    }
    
}
