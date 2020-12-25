package entity.order;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "order_status")
public class OrderStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 55)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Collection<OrderItems> orderItemsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId")
    private Collection<Orders> ordersCollection;

    public OrderStatus() {
    }

    public OrderStatus(Long id) {
        this.id = id;
    }

    public OrderStatus(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}