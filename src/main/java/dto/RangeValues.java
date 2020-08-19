package dto;

public class RangeValues {
    private Double smallest;
    private Double largest;

    public RangeValues(Double smallest, Double largest) {
        this.smallest = smallest;
        this.largest = largest;
    }

    public Double getSmallest() {
        return smallest;
    }

    public void setSmallest(Double smallest) {
        this.smallest = smallest;
    }

    public Double getLargest() {
        return largest;
    }

    public void setLargest(Double largest) {
        this.largest = largest;
    }
}
