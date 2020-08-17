
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "username")

    private String username;
    @Basic(optional = false)

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "password")
    private String password;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "password_salt")
    private String passwordSalt;

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "first_name")
    private String firstName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "last_name")
    private String lastName;

    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<BillingAddress> billingAddressCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "managerId")
    private Collection<ShopManagers> shopManagersCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerId")
    private Collection<Shops> shopsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<Orders> ordersCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<ShopReviews> shopReviewsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<ProductReviews> productReviewsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<ShippingAddress> shippingAddressCollection;

    public Users() {
    }

    public Users(Long id) {
        this.id = id;
    }

    public Users(Long id, String username, String password, String passwordSalt, String email, String firstName, String lastName, Date created) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwordSalt = passwordSalt;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    public Collection<BillingAddress> getBillingAddressCollection() {
        return billingAddressCollection;
    }

    public void setBillingAddressCollection(Collection<BillingAddress> billingAddressCollection) {
        this.billingAddressCollection = billingAddressCollection;
    }


    public Collection<ShopManagers> getShopManagersCollection() {
        return shopManagersCollection;
    }

    public void setShopManagersCollection(Collection<ShopManagers> shopManagersCollection) {
        this.shopManagersCollection = shopManagersCollection;
    }


    public Collection<Shops> getShopsCollection() {
        return shopsCollection;
    }

    public void setShopsCollection(Collection<Shops> shopsCollection) {
        this.shopsCollection = shopsCollection;
    }


    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }


    public Collection<ShopReviews> getShopReviewsCollection() {
        return shopReviewsCollection;
    }

    public void setShopReviewsCollection(Collection<ShopReviews> shopReviewsCollection) {
        this.shopReviewsCollection = shopReviewsCollection;
    }


    public Collection<ProductReviews> getProductReviewsCollection() {
        return productReviewsCollection;
    }

    public void setProductReviewsCollection(Collection<ProductReviews> productReviewsCollection) {
        this.productReviewsCollection = productReviewsCollection;
    }


    public Collection<ShippingAddress> getShippingAddressCollection() {
        return shippingAddressCollection;
    }

    public void setShippingAddressCollection(Collection<ShippingAddress> shippingAddressCollection) {
        this.shippingAddressCollection = shippingAddressCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Users[ id=" + id + " ]";
    }
    
}
