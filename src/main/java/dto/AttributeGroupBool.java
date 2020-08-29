package dto;

public class AttributeGroupBool extends AttributeGroup {
    private int boolVal;


    public AttributeGroupBool(Long attributeID, String attributeCode, Long count, int boolVal) {
        super(attributeID, attributeCode, count);
        this.boolVal = boolVal;
    }

    public int getBoolVal() {
        return boolVal;
    }

    public void setBoolVal(int boolVal) {
        this.boolVal = boolVal;
    }
}
