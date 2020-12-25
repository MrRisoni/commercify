package entity.order;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
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
}