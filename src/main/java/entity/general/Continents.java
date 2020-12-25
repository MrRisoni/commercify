package entity.general;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(name = "continents")
public class Continents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column
    private String title;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column
    private String code;

    public Continents() {
    }

    public Continents(Long id) {
        this.id = id;
    }

    public Continents(Long id, String title, String code) {
        this.id = id;
        this.title = title;
        this.code = code;
    }
}