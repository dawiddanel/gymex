package pl.danel.gymex.domain.person;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Firstname {

    public static final int FIRSTNAME_MAX_LENGTH = 50;

    @Getter
    @Column
    private String value;

    public static Firstname of(String value) {
        return new Firstname(value);
    }

    private Firstname(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "Firstname is null");
        DomainAsserts.assertArgumentNotTooLong(value, FIRSTNAME_MAX_LENGTH, "Firstname cannot be longer then " + FIRSTNAME_MAX_LENGTH + " characters");
        this.value = value;
    }

}

