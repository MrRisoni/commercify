package dto;

import java.math.BigDecimal;

public class TopProduct extends Top{

    public TopProduct() {
    }

    public TopProduct(Long id, String title, Long itemsSold, BigDecimal totalNet) {
        super(id, title, itemsSold, totalNet);
    }
}