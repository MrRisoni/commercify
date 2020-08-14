/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "shop_belongs_categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShopBelongsCategories.findAll", query = "SELECT s FROM ShopBelongsCategories s")})
public class ShopBelongsCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disable_cod")
    private boolean disableCod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "show_order")
    private int showOrder;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shopBelongsCategories", fetch = FetchType.LAZY)
    private Set<ShopProductCateogoryTaxes> shopProductCateogoryTaxesSet;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shop shop;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopCategory shopCategory;

    public ShopBelongsCategories() {
    }

    public ShopBelongsCategories(Long id) {
        this.id = id;
    }

    public ShopBelongsCategories(Long id, boolean disableCod, String thumbnailUrl, int showOrder) {
        this.id = id;
        this.disableCod = disableCod;
        this.thumbnailUrl = thumbnailUrl;
        this.showOrder = showOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getDisableCod() {
        return disableCod;
    }

    public void setDisableCod(boolean disableCod) {
        this.disableCod = disableCod;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    @XmlTransient
    public Set<ShopProductCateogoryTaxes> getShopProductCateogoryTaxesSet() {
        return shopProductCateogoryTaxesSet;
    }

    public void setShopProductCateogoryTaxesSet(Set<ShopProductCateogoryTaxes> shopProductCateogoryTaxesSet) {
        this.shopProductCateogoryTaxesSet = shopProductCateogoryTaxesSet;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
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
        if (!(object instanceof ShopBelongsCategories)) {
            return false;
        }
        ShopBelongsCategories other = (ShopBelongsCategories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "core.entity.ShopBelongsCategories[ id=" + id + " ]";
    }
    
}
