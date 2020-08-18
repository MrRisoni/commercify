package pojo;

import java.util.List;

public class BrowsePojo {

    private ProductOrderBy orderBy;
    private List<ProductFilterPojo> filters;
    private int minPrice;
    private int maxPrice;
    private int perPage;
    private int currentPage;

    public BrowsePojo(ProductOrderBy orderBy, List<ProductFilterPojo> filters, int minPrice, int maxPrice, int perPage, int currentPage) {
        this.orderBy = orderBy;
        this.filters = filters;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.perPage = perPage;
        this.currentPage = currentPage;
    }

    public ProductOrderBy getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(ProductOrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public List<ProductFilterPojo> getFilters() {
        return filters;
    }

    public void setFilters(List<ProductFilterPojo> filters) {
        this.filters = filters;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
