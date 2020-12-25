package entity.shop;

import entity.product.Products;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Data
@Entity
@Table(name = "suppliers_supplies")
public class SuppliersSupplies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Products productId;

    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShopSuppliers supplierId;

    public SuppliersSupplies() {
    }


}