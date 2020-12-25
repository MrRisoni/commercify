package dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductAttributeValDto {
    private Long attributeKey;
    private String value;
    private BigDecimal valueNumeric;
    private int valueBoolean;
   // private ProductCategoryAttributesDto attributeId;
}
