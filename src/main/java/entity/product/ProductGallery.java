package entity.product;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "product_gallery")
public class ProductGallery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IShopProduct.class)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "file_path")
    @JsonView(JackSonViewer.IShopProduct.class)
    private String filePath;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imageId")
    private Collection<ProductGalleryTag> productGalleryTagCollection;

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
}