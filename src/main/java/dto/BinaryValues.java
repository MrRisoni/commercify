package dto;

import lombok.Data;

@Data
public class BinaryValues {
    private int yes;
    private int no;

    public BinaryValues(int yes, int no) {
        this.yes = yes;
        this.no = no;
    }
}
