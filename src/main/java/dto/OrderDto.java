package dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
public class OrderDto {
    private Long id;
    private String shopName;
    private Date createdAt;
    private Date updatedAt;
    private String customerName;
    private String customerEmail;
    private BigDecimal total;
    private BigDecimal net;
    private BigDecimal taxes;
    private BigDecimal shipping;
    private BigDecimal courrierFees;
    private BigDecimal commission;
    private String currency;
    private BigDecimal currency_rate;
    private boolean success;
    private boolean isVoid;
    private boolean refund;
    private BillingAddressDto billingAddressId;
    private ShippingAddressDto shippingAddressId;
    private PaymentMethodDto payMethodId;
    private OrderStatusDto statusId;
    private ShopDto shopId;
    private Collection<OrderStatusHistoryDto> statusHistory;
    private Collection<OrderItemDto> orderItemsCollection;

    public OrderDto() {
    }


}