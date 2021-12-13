package pl.danel.gymex.domain.gym.address;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Country {

    public static final int COUNTRY_MAX_LENGTH = 50;

    @Getter
    @Column
    private String value;

    public static Country of(String value) {
        return new Country(value);
    }

    private Country(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "Country is null");
        DomainAsserts.assertArgumentNotTooLong(value, COUNTRY_MAX_LENGTH, "Country cannot be longer then " + COUNTRY_MAX_LENGTH + " characters");
        this.value = value;
    }

}
