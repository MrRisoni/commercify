package dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductReviewDto {
    private Long id;
    private BigDecimal stars;
    private String comment;
    private Date created;
    private UserDto userId;

}
