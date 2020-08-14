
package entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "product_gallery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductGallery.findAll", query = "SELECT p FROM ProductGallery p")})
public class ProductGallery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "file_path")
    private String filePath;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imageId", fetch = FetchType.LAZY)
    private Set<ProductGalleryTag> productGalleryTagSet;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Products productId;

    public ProductGallery() {
    }

    public ProductGallery(Long id) {
        this.id = id;
    }

    public ProductGallery(Long id, String filePath) {
        this.id = id;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @XmlTransient
    public Set<ProductGalleryTag> getProductGalleryTagSet() {
        return productGalleryTagSet;
    }

    public void setProductGalleryTagSet(Set<ProductGalleryTag> productGalleryTagSet) {
        this.productGalleryTagSet = productGalleryTagSet;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
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
        if (!(object instanceof ProductGallery)) {
            return false;
        }
        ProductGallery other = (ProductGallery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProductGallery[ id=" + id + " ]";
    }
    
}
