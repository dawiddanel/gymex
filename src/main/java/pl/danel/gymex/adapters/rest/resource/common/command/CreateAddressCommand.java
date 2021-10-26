package pl.danel.gymex.adapters.rest.resource.common.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressCommand {
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private String buildingNumber;
}
