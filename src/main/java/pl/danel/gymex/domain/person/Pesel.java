package pl.danel.gymex.domain.person;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Pesel {

    private static final String PESEL_REGEX = "^\\d{11}$";

    @Getter
    @Column
    private String value;

    public static Pesel of(String value) {
        return new Pesel(value);
    }

    private Pesel(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "Pesel is null");
        DomainAsserts.assertArgumentMatchesPattern(PESEL_REGEX, value, "Pesel is malmorfed");
        this.value = value;
    }

}