package dto;

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

    public Long getAttributeID() {
        return attributeID;
    }

    public void setAttributeID(Long attributeID) {
        this.attributeID = attributeID;
    }

    public String getAttributeCode() {
        return attributeCode;
    }

    public void setAttributeCode(String attributeCode) {
        this.attributeCode = attributeCode;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
