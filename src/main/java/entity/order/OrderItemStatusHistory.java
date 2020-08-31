package entity.order;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "order_items_status_history")
public class OrderItemStatusHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(JackSonViewer.IOrder.class)
    private Date createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    @JsonView(JackSonViewer.IOrder.class)
    private OrderStatus statusObj;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private OrderItems itemObj;

    public OrderItemStatusHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public OrderStatus getStatusObj() {
        return statusObj;
    }

    public void setStatusObj(OrderStatus statusObj) {
        this.statusObj = statusObj;
    }

    public OrderItems getItemObj() {
        return itemObj;
    }

    public void setItemObj(OrderItems itemObj) {
        this.itemObj = itemObj;
    }
}
