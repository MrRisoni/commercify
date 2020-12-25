package dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Analytics {
    private Long itemsSold;
    private Long ordersNum;
    private BigDecimal gross;
    private BigDecimal net;
    private List<TopCategory> topCategories;
    private List<TopProduct> topProducts;

    public Analytics(Long itemsSold, Long ordersNum, BigDecimal gross, BigDecimal net) {
        this.itemsSold = itemsSold;
        this.ordersNum = ordersNum;
        this.gross = gross;
        this.net = net;
    }

}