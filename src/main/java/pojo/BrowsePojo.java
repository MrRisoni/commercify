package pojo;

import java.util.List;

public class BrowsePojo {

    private ProductOrderBy orderBy;
    private List<ProductFilterPojo> filters;

    public BrowsePojo(ProductOrderBy orderBy, List<ProductFilterPojo> filters) {
        this.orderBy = orderBy;
        this.filters = filters;
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
}
