package entity.shipping;

import entity.general.GlobeRegions;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Data
@Entity
@Table(name = "shipping_zones_regions")
public class ShippingZonesRegions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GlobeRegions regionId;

    @JoinColumn(name = "zone_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ShippingZones zoneId;

    public ShippingZonesRegions() {
    }

}