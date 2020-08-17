
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "product_gallery_tag")
@NamedQueries({
    @NamedQuery(name = "ProductGalleryTag.findAll", query = "SELECT p FROM ProductGalleryTag p")})
public class ProductGalleryTag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "tag")
    private String tag;
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductGallery imageId;

    public ProductGalleryTag() {
    }

    public ProductGalleryTag(Long id) {
        this.id = id;
    }

    public ProductGalleryTag(Long id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ProductGallery getImageId() {
        return imageId;
    }

    public void setImageId(ProductGallery imageId) {
        this.imageId = imageId;
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
        if (!(object instanceof ProductGalleryTag)) {
            return false;
        }
        ProductGalleryTag other = (ProductGalleryTag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProductGalleryTag[ id=" + id + " ]";
    }
    
}
