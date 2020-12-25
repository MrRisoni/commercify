package entity.order;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
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
@Table(name = "shipping_address")
public class ShippingAddress implements Serializable {

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
    @JsonView(JackSonViewer.IOrder.class)
    private String countryCode;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "contact_mobile")
    @JsonView(JackSonViewer.IOrder.class)
    private String contactMobile;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private String city;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "full_name")
    @JsonView(JackSonViewer.IOrder.class)
    private String fullName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private String address;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "street_no")
    @JsonView(JackSonViewer.IOrder.class)
    private String streetNo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "post_code")
    @JsonView(JackSonViewer.IOrder.class)
    private String postCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shippingAddressId")
    private Collection<Orders> ordersCollection;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users userId;

    @JoinColumn(name = "region_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GlobeRegions regionId;

    public ShippingAddress() {
    }
}