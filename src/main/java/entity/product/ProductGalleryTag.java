package entity.product;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "product_gallery_tag")
public class ProductGalleryTag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column
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
}