package dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
public class ProductsDto {

    private  Long id;
    private  String title;
    private  String thumbnailUrl;
    private  BigDecimal price;
    private  BigDecimal kilos;
    private  long stock;
    private  Date created;
    private  Date updated;
    private  long totalOrders;
    private  long totalClicks;
    private  BigDecimal avgRating;
    private Collection<ProductGalleryDto> productGalleryCollection;
    private Collection<ProductReviewDto> productReviewsCollection;
    private ManufacturerDto manufacturerId;

    public ProductsDto() {
    }

    public ProductsDto(Long id, @NotNull @Size(min = 1, max = 55) String title, @NotNull @Size(min = 1, max = 255) String thumbnailUrl, @NotNull BigDecimal price, @NotNull BigDecimal kilos, @NotNull long stock, @NotNull Date created, Date updated, @NotNull long totalOrders, @NotNull long totalClicks, @NotNull BigDecimal avgRating) {
        this.id = id;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.kilos = kilos;
        this.stock = stock;
        this.created = created;
        this.updated = updated;
        this.totalOrders = totalOrders;
        this.totalClicks = totalClicks;
        this.avgRating = avgRating;
    }

}
