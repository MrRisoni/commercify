package pojo;

public class ProductOrderBy {
    private String orderBy;
    private String sortOrder;

    public ProductOrderBy(String orderBy, String sort) {
        this.orderBy = orderBy;
        this.sortOrder = sort;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
