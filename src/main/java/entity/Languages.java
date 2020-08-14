
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "languages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Languages.findAll", query = "SELECT l FROM Languages l")})
public class Languages implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageId", fetch = FetchType.LAZY)
    private Set<ProductTags> productTagsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageId", fetch = FetchType.LAZY)
    private Set<ProductDescription> productDescriptionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageId", fetch = FetchType.LAZY)
    private Set<ShopLanguages> shopLanguagesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageId", fetch = FetchType.LAZY)
    private Set<ProductAttributes> productAttributesSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "defaultLangId", fetch = FetchType.LAZY)
    private Set<Shops> shopsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageId", fetch = FetchType.LAZY)
    private Set<ProductGalleryTag> productGalleryTagSet;

    public Languages() {
    }

    public Languages(Long id) {
        this.id = id;
    }

    public Languages(Long id, String title, String code) {
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
    public Set<ProductAttributes> getProductAttributesSet() {
        return productAttributesSet;
    }

    public void setProductAttributesSet(Set<ProductAttributes> productAttributesSet) {
        this.productAttributesSet = productAttributesSet;
    }

    @XmlTransient
    public Set<Shops> getShopsSet() {
        return shopsSet;
    }

    public void setShopsSet(Set<Shops> shopsSet) {
        this.shopsSet = shopsSet;
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
        if (!(object instanceof Languages)) {
            return false;
        }
        Languages other = (Languages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Languages[ id=" + id + " ]";
    }
    
}
