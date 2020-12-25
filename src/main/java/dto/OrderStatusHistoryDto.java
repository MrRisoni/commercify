package dto;

import lombok.Data;
import java.util.Date;

@Data
public class OrderStatusHistoryDto {
    private Date createdAt;
    private OrderStatusDto statusObj;

}
