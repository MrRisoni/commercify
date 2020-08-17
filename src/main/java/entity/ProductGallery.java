
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




@Entity
@Table(name = "product_gallery")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imageId")
    private Collection<ProductGalleryTag> productGalleryTagCollection;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
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


    public Collection<ProductGalleryTag> getProductGalleryTagCollection() {
        return productGalleryTagCollection;
    }

    public void setProductGalleryTagCollection(Collection<ProductGalleryTag> productGalleryTagCollection) {
        this.productGalleryTagCollection = productGalleryTagCollection;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
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
