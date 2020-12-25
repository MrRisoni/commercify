package dto;

import lombok.Data;

@Data
public class AttributeGroup {
    private Long attributeID;
    private String attributeCode;
    private Long count;

    public AttributeGroup() {
    }

    public AttributeGroup(Long attributeID, String attributeCode, Long count) {
        this.attributeID = attributeID;
        this.attributeCode = attributeCode;
        this.count = count;
    }

}
