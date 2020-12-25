package entity.shipping;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shipping_region_zips")
public class ShippingRegionsZipCodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "zip_code")
    private String zipCode;

    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShippingZonesRegions regionId;

    public ShippingRegionsZipCodes() {
    }

    public ShippingRegionsZipCodes(Long id) {
        this.id = id;
    }

    public ShippingRegionsZipCodes(Long id, String zipCode) {
        this.id = id;
        this.zipCode = zipCode;
    }

}