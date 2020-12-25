package entity.shop;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "shop_reviews")
public class ShopReviews implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column
    private BigDecimal stars;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column
    private String comment;

    @Basic(optional = false)
    @NotNull
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created;

    @JoinColumn(name = "shop_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Shops shopId;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users userId;

    public ShopReviews() {
    }

    public ShopReviews(Long id) {
        this.id = id;
    }

    public ShopReviews(Long id, BigDecimal stars, String comment, Date created) {
        this.id = id;
        this.stars = stars;
        this.comment = comment;
        this.created = created;
    }
}