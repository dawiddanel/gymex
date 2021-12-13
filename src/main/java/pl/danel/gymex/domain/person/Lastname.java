package pl.danel.gymex.domain.person;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Lastname {

    public static final int LASTNAME_MAX_LENGTH = 50;

    @Getter
    @Column
    private String value;

    public static Lastname of(String value) {
        return new Lastname(value);
    }

    private Lastname(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "Lastname is null");
        DomainAsserts.assertArgumentNotTooLong(value, LASTNAME_MAX_LENGTH, "Lastname cannot be longer then " + LASTNAME_MAX_LENGTH + " characters");
        this.value = value;
    }

}
