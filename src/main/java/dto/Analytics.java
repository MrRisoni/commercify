package dto;

import java.math.BigDecimal;
import java.util.List;

public class Analytics {
    private Long itemsSold;
    private Long ordersNum;
    private BigDecimal gross;
    private BigDecimal net;
    private List<TopCategory> topCategories;

    public Analytics(Long itemsSold, Long ordersNum, BigDecimal gross, BigDecimal net) {
        this.itemsSold = itemsSold;
        this.ordersNum = ordersNum;
        this.gross = gross;
        this.net = net;
    }

    public Long getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(Long itemsSold) {
        this.itemsSold = itemsSold;
    }

    public Long getOrdersNum() {
        return ordersNum;
    }

    public void setOrdersNum(Long ordersNum) {
        this.ordersNum = ordersNum;
    }

    public BigDecimal getGross() {
        return gross;
    }

    public void setGross(BigDecimal gross) {
        this.gross = gross;
    }

    public BigDecimal getNet() {
        return net;
    }

    public void setNet(BigDecimal net) {
        this.net = net;
    }

    public List<TopCategory> getTopCategories() {
        return topCategories;
    }

    public void setTopCategories(List<TopCategory> topCategories) {
        this.topCategories = topCategories;
    }
}
