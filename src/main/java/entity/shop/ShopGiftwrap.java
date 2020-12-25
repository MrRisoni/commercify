package entity.shop;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shop_giftwrap")
public class ShopGiftwrap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column(name = "image_path")
    private String imagePath;

    @Basic(optional = false)
    @NotNull
    @Column
    private boolean active;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    public ShopGiftwrap() {
    }

}