package dto;

import java.math.BigDecimal;

public class TopCategory extends Top{

    public TopCategory() {
    }

    public TopCategory(Long id, String title, Long itemsSold, BigDecimal totalNet) {
        super(id, title, itemsSold, totalNet);
    }
}
