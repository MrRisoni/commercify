package entity.shop;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Data
@Entity
@Table(name = "shop_managers")
public class ShopManagers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users managerId;

    public ShopManagers() {
    }
}