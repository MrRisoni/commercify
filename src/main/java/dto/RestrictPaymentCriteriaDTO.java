package dto;

public class RestrictPaymentCriteriaDTO {
    private Long id;
    private String parameterTitle;
    private String operatorCode;
    private String val;

    public RestrictPaymentCriteriaDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParameterTitle() {
        return parameterTitle;
    }

    public void setParameterTitle(String parameterTitle) {
        this.parameterTitle = parameterTitle;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
