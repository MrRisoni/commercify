package entity.order;



import entity.shop.Users;
import entity.general.GlobeRegions;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "billing_address")
public class BillingAddress implements Serializable {
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
    @Size(min = 1, max = 2)
    @Column(name = "contact_mobile")
    private String contactMobile;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column
    private String city;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "full_name")
    private String fullName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column
    private String address;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "street_no")
    private String streetNo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "post_code")
    private String postCode;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "billingAddressId")
    private Collection<Orders> ordersCollection;

    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GlobeRegions regionId;

    public BillingAddress() {
    }

    public BillingAddress(Long id) {
        this.id = id;
    }
}