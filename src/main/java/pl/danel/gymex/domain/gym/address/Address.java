package pl.danel.gymex.domain.gym.address;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private String buildingNumber;
}
