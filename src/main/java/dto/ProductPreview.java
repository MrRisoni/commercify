package dto;

import java.math.BigDecimal;

public class ProductPreview {
    private long id;
    private String title;
    private String thumbnailUrl;
    private BigDecimal price;

    public ProductPreview(long id, String title, String thumbnailUrl, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
