package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductPreviewDto {
    private long id;
    private String title;
    private String thumbnailUrl;
    private BigDecimal price;

    public ProductPreviewDto(long id, String title, String thumbnailUrl, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
    }
}