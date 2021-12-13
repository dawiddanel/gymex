package pl.danel.gymex.domain.gym.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.gym.command.CreateAddress;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "COUNTRY"))
    private Country country;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "CITY"))
    private City city;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "POSTAL_CODE"))
    private PostalCode postalCode;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "STREET"))
    private Street street;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "BUILDING_NUMBER"))
    private BuildingNumber buildingNumber;

    private Address(CreateAddress command) {
        this.country = Country.of(command.getCountry());
        this.city = City.of(command.getCity());
        this.postalCode = PostalCode.of(command.getPostalCode());
        this.street = Street.of(command.getStreet());
        this.buildingNumber = BuildingNumber.of(command.getBuildingNumber());
    }

    public static Address create(CreateAddress command) {
        return new Address(command);
    }
}
