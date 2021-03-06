package entity.shipping;

import entity.shop.Shops;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shop_disable_zip_codes")
public class ShopDisableZipCodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "country_code")
    private String countryCode;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column
    private String zip;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    public ShopDisableZipCodes() {
    }

    public ShopDisableZipCodes(Long id) {
        this.id = id;
    }

    public ShopDisableZipCodes(Long id, String countryCode, String zip) {
        this.id = id;
        this.countryCode = countryCode;
        this.zip = zip;
    }
}