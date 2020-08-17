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
@Table(name = "product_category_attributes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductCategoryAttributes.findAll", query = "SELECT p FROM ProductCategoryAttributes p")})
public class ProductCategoryAttributes implements Serializable {

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
    @Column(name = "filterable")
    private short filterable;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rangeable")
    private short rangeable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attributeId")
    private Collection<ProductAttributesValues> productAttributesValuesCollection;
    @JoinColumn(name = "product_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductCategories productCategoryId;
    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shops shopId;

    public ProductCategoryAttributes() {
    }

    public ProductCategoryAttributes(Long id) {
        this.id = id;
    }

    public ProductCategoryAttributes(Long id, String title, short filterable, short rangeable) {
        this.id = id;
        this.title = title;
        this.filterable = filterable;
        this.rangeable = rangeable;
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

    public short getFilterable() {
        return filterable;
    }

    public void setFilterable(short filterable) {
        this.filterable = filterable;
    }

    public short getRangeable() {
        return rangeable;
    }

    public void setRangeable(short rangeable) {
        this.rangeable = rangeable;
    }

    @XmlTransient
    public Collection<ProductAttributesValues> getProductAttributesValuesCollection() {
        return productAttributesValuesCollection;
    }

    public void setProductAttributesValuesCollection(Collection<ProductAttributesValues> productAttributesValuesCollection) {
        this.productAttributesValuesCollection = productAttributesValuesCollection;
    }

    public ProductCategories getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(ProductCategories productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Shops getShopId() {
        return shopId;
    }

    public void setShopId(Shops shopId) {
        this.shopId = shopId;
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
        if (!(object instanceof ProductCategoryAttributes)) {
            return false;
        }
        ProductCategoryAttributes other = (ProductCategoryAttributes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProductCategoryAttributes[ id=" + id + " ]";
    }
    
}
