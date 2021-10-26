package pl.danel.gymex.application.common.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressDto {
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private String buildingNumber;
}
