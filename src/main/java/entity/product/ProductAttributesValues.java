package entity.product;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "product_attributes_values")
public class ProductAttributesValues implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IShopProduct.class)
    private Long id;

    @Column(name = "attribute_id")
    private Long attributeKey;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column(name = "value")
    @JsonView(JackSonViewer.IShopProduct.class)
    private String value;

    @JsonView({JackSonViewer.IOrder.class, JackSonViewer.IShopProduct.class})
    @Column(name = "value_numeric")
    private BigDecimal valueNumeric;

    @Column(name = "value_boolean")
    @JsonView({JackSonViewer.IOrder.class, JackSonViewer.IShopProduct.class})
    private int valueBoolean;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Products productId;

    @JoinColumn(name = "attribute_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonView(JackSonViewer.IShopProduct.class)
    private ProductCategoryAttributes attributeId;

    public ProductAttributesValues() {
    }

    public ProductAttributesValues(Long id) {
        this.id = id;
    }

}