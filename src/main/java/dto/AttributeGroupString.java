package dto;

public class AttributeGroupString extends AttributeGroup {
    private String strVal;


    public AttributeGroupString(Long attributeID, String attributeCode, Long count, String strVal) {
        super(attributeID, attributeCode, count);
        this.strVal = strVal;
    }

    public String getStrVal() {
        return strVal;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }
}
