package dto;

import lombok.Data;

@Data
public class AttributeGroupBool extends AttributeGroup {
    private int boolVal;

    public AttributeGroupBool(Long attributeID, String attributeCode, Long count, int boolVal) {
        super(attributeID, attributeCode, count);
        this.boolVal = boolVal;
    }

}
