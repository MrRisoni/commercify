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
@Table(name = "languages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l")})
public class Language implements Serializable {

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
    @Size(min = 1, max = 5)
    @Column(name = "code")
    private String code;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language", fetch = FetchType.LAZY)
    private Set<ProductTags> productTagsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language", fetch = FetchType.LAZY)
    private Set<ProductDescription> productDescriptionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language", fetch = FetchType.LAZY)
    private Set<ShopLanguages> shopLanguagesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language", fetch = FetchType.LAZY)
    private Set<ProductAttribute> productAttributeSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language", fetch = FetchType.LAZY)
    private Set<Shop> shopSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "language", fetch = FetchType.LAZY)
    private Set<ProductGalleryTag> productGalleryTagSet;

    public Language() {
    }

    public Language(Long id) {
        this.id = id;
    }

    public Language(Long id, String title, String code) {
        this.id = id;
        this.title = title;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlTransient
    public Set<ProductTags> getProductTagsSet() {
        return productTagsSet;
    }

    public void setProductTagsSet(Set<ProductTags> productTagsSet) {
        this.productTagsSet = productTagsSet;
    }

    @XmlTransient
    public Set<ProductDescription> getProductDescriptionSet() {
        return productDescriptionSet;
    }

    public void setProductDescriptionSet(Set<ProductDescription> productDescriptionSet) {
        this.productDescriptionSet = productDescriptionSet;
    }

    @XmlTransient
    public Set<ShopLanguages> getShopLanguagesSet() {
        return shopLanguagesSet;
    }

    public void setShopLanguagesSet(Set<ShopLanguages> shopLanguagesSet) {
        this.shopLanguagesSet = shopLanguagesSet;
    }

    @XmlTransient
    public Set<ProductAttribute> getProductAttributeSet() {
        return productAttributeSet;
    }

    public void setProductAttributeSet(Set<ProductAttribute> productAttributeSet) {
        this.productAttributeSet = productAttributeSet;
    }

    @XmlTransient
    public Set<Shop> getShopSet() {
        return shopSet;
    }

    public void setShopSet(Set<Shop> shopSet) {
        this.shopSet = shopSet;
    }

    @XmlTransient
    public Set<ProductGalleryTag> getProductGalleryTagSet() {
        return productGalleryTagSet;
    }

    public void setProductGalleryTagSet(Set<ProductGalleryTag> productGalleryTagSet) {
        this.productGalleryTagSet = productGalleryTagSet;
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
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "core.entity.Language[ id=" + id + " ]";
    }
    
}
