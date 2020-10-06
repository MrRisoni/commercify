
package entity.order;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import entity.shop.Users;
import entity.general.GlobeRegions;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    public GlobeRegions getRegionId() {
        return regionId;
    }

    public void setRegionId(GlobeRegions regionId) {
        this.regionId = regionId;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }
}
