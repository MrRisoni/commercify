package dto;

import lombok.Data;

@Data
public class BillingAddressDto {
    private String countryCode;
    private String contactMobile;
    private String city;
    private String fullName;
    private String address;
    private String streetNo;
    private String postCode;
}
