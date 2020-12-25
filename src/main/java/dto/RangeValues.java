package dto;

import lombok.Data;

@Data
public class RangeValues {
    private Double smallest;
    private Double largest;

    public RangeValues(Double smallest, Double largest) {
        this.smallest = smallest;
        this.largest = largest;
    }
}