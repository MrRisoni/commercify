
package entity.product;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "product_gallery_tag")
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
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
}