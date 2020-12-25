package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimpleProduct {
    private Long id;
    private String code;
    private String title;
    private String thumbnailUrl;
    private BigDecimal price;
    private   BigDecimal avgRating;

    public SimpleProduct() {
    }

    public SimpleProduct(Long id, String code, String title, String thumbnailUrl, BigDecimal price, BigDecimal avgRating) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.avgRating = avgRating;
    }
}
