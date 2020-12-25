package dto;

import lombok.Data;

@Data
public class AttributeGroupString extends AttributeGroup {
    private String strVal;

    public AttributeGroupString(Long attributeID, String attributeCode, Long count, String strVal) {
        super(attributeID, attributeCode, count);
        this.strVal = strVal;
    }
}
