package dto;

import lombok.Data;

@Data
public class RestrictPaymentCriteriaDTO {
    private Long id;
    private String parameterTitle;
    private String operatorCode;
    private String val;

    public RestrictPaymentCriteriaDTO() {
    }
}