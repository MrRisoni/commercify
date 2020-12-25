package entity.general;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "countries")
public class Countries implements Serializable {

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

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column
    private String continent;

    public Countries() {
    }

    public Countries(Long id) {
        this.id = id;
    }

    public Countries(Long id, String title, String code, String continent) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.continent = continent;
    }
}