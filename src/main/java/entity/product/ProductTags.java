
package entity.product;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "product_tags")
public class ProductTags implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @JsonView(JackSonViewer.IShopProduct.class)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "tag")
    @JsonView(JackSonViewer.IShopProduct.class)
    private String tag;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Products productId;

    public ProductTags() {
    }

    public ProductTags(Long id) {
        this.id = id;
    }

    public ProductTags(Long id, String tag) {
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

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

}