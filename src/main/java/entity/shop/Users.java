
package entity.shop;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import entity.order.BillingAddress;
import entity.order.Orders;
import entity.order.ShippingAddress;
import entity.product.ProductReviews;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView({JackSonViewer.IShopProduct.class, JackSonViewer.IOrder.class})
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @JsonView(JackSonViewer.IShopProduct.class)
    @Column
    private String username;
    @Basic(optional = false)

    @NotNull
    @Size(min = 1, max = 40)
    @Column
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
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private String email;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "first_name")
    @JsonView(JackSonViewer.IOrder.class)
    private String firstName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "last_name")
    @JsonView(JackSonViewer.IOrder.class)
    private String lastName;

    @Basic(optional = false)
    @NotNull
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column
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
}
