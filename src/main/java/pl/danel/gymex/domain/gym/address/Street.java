package pl.danel.gymex.domain.gym.address;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Street {

    public static final int STREET_MAX_LENGTH = 50;

    @Getter
    @Column
    private String value;

    public static Street of(String value) {
        return new Street(value);
    }

    private Street(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "Street is null");
        DomainAsserts.assertArgumentNotTooLong(value, STREET_MAX_LENGTH, "Street cannot be longer then " + STREET_MAX_LENGTH + " characters");
        this.value = value;
    }

}
