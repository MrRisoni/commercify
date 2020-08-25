
package entity.product;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "product_gallery")
public class ProductGallery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
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
}