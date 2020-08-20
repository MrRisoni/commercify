package dto;

import java.math.BigDecimal;

public class Top {
    private Long Id;
    private String title;
    private Long itemsSold;
    private BigDecimal totalNet;

    public Top() {
    }

    public Top(Long id, String title, Long itemsSold, BigDecimal totalNet) {
        Id = id;
        this.title = title;
        this.itemsSold = itemsSold;
        this.totalNet = totalNet;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getQuantity() {
        return itemsSold;
    }

    public void setQuantity(Long quantity) {
        this.itemsSold = quantity;
    }

    public BigDecimal getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(BigDecimal totalNet) {
        this.totalNet = totalNet;
    }
}
