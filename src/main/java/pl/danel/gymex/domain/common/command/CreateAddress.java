package pl.danel.gymex.domain.common.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateAddress {
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private String buildingNumber;
}
