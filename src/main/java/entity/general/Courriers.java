package entity.general;

import com.fasterxml.jackson.annotation.JsonView;
import entity.JackSonViewer;
import entity.shipping.ShopCouriers;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "courriers")
public class Courriers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column
    @JsonView(JackSonViewer.IOrder.class)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courierId")
    private Collection<ShopCouriers> shopCouriersCollection;

    public Courriers() {
    }

    public Courriers(Long id) {
        this.id = id;
    }

    public Courriers(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}