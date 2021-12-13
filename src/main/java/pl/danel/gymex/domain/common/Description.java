package pl.danel.gymex.domain.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Description {

    public static final int DESCRIPTION_MAX_LENGTH = 50;

    @Getter
    @Column
    private String value;

    public static Description of(String value) {
        return new Description(value);
    }

    private Description(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "Description is null");
        DomainAsserts.assertArgumentNotTooLong(value, DESCRIPTION_MAX_LENGTH, "Description cannot be longer then " + DESCRIPTION_MAX_LENGTH + " characters");
        this.value = value;
    }

}
