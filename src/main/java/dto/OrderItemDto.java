package dto;

import entity.product.Products;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
public class OrderItemDto {
    private int quantity;
    private String trackingNo;
    private BigDecimal netPrice;
    private BigDecimal taxes;
    private BigDecimal giftCost;
    private boolean success;
    private boolean isVoid;
    private boolean refund;
    private Date dispatchedOn;
    private Date expectedOn;
    private Date arrivedOn;
    private ProductsDto productId;
    private OrderStatusDto statusId;
    private Collection<OrderStatusHistoryDto> statusHistory;

}
