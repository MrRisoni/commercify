package dto;

import java.math.BigDecimal;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(BigDecimal avgRating) {
        this.avgRating = avgRating;
    }
}
