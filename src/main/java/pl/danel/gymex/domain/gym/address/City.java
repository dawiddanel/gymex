package pl.danel.gymex.domain.gym.address;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class City {

    public static final int CITY_MAX_LENGTH = 50;

    @Getter
    @Column
    private String value;

    public static City of(String value) {
        return new City(value);
    }

    private City(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "City is null");
        DomainAsserts.assertArgumentNotTooLong(value, CITY_MAX_LENGTH, "City cannot be longer then " + CITY_MAX_LENGTH + " characters");
        this.value = value;
    }

}
