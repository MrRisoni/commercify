package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
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
}