
package entity;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "product_attributes_values")
public class ProductAttributesValues implements Serializable {

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
    @Column(name = "value")
    @JsonView(JackSonViewer.IShopProduct.class)
    private String value;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Products productId;

    @JoinColumn(name = "attribute_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonView(JackSonViewer.IShopProduct.class)
    private ProductCategoryAttributes attributeId;

    public ProductAttributesValues() {
    }

    public ProductAttributesValues(Long id) {
        this.id = id;
    }

    public ProductAttributesValues(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public ProductCategoryAttributes getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(ProductCategoryAttributes attributeId) {
        this.attributeId = attributeId;
    }


}
