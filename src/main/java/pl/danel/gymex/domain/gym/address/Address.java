package pl.danel.gymex.domain.gym.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.gym.command.CreateAddress;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private String buildingNumber;

    private Address(CreateAddress command) {
        this.country = command.getCountry();
        this.city = command.getCity();
        this.postalCode = command.getPostalCode();
        this.street = command.getStreet();
        this.buildingNumber = command.getBuildingNumber();
    }

    public static Address create(CreateAddress command) {
        return new Address(command);
    }
}
