package entity.order;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "orders_status_history")
public class OrderStatusHistory {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private Long id;

    @Basic(optional = false)
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
    @JoinColumn(name = "order_id")
    private Orders orderObj;

    public OrderStatusHistory() {
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

    public Orders getOrderObj() {
        return orderObj;
    }

    public void setOrderObj(Orders orderObj) {
        this.orderObj = orderObj;
    }
}
