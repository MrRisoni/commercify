package entity.product;


import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_attribute_units")
public class ProductAttributeUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IShopProduct.class)
    private Long id;

    @Column
    @JsonView(JackSonViewer.IShopProduct.class)
    private String units;

    @JoinColumn(name = "attribute_id", referencedColumnName = "id")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private ProductCategoryAttributes attrObj;

    public ProductAttributeUnit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public ProductCategoryAttributes getAttrObj() {
        return attrObj;
    }

    public void setAttrObj(ProductCategoryAttributes attrObj) {
        this.attrObj = attrObj;
    }
}
