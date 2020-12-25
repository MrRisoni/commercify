package entity.rules;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "restrict_payment_parameters")
public class RestrictPaymentParameters {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column
    private String title;

    @Basic(optional = false)
    @NotNull
    @Column
    private String code;

    public RestrictPaymentParameters() {
    }

    public RestrictPaymentParameters(Long id, @NotNull String title, @NotNull String code) {
        this.id = id;
        this.title = title;
        this.code = code;
    }

}
