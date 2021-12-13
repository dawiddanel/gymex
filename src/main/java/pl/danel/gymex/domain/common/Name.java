package pl.danel.gymex.domain.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Name {

    public static final int NAME_MAX_LENGTH = 50;

    @Getter
    @Column
    private String value;

    public static Name of(String value) {
        return new Name(value);
    }

    private Name(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "Name is null");
        DomainAsserts.assertArgumentNotTooLong(value, NAME_MAX_LENGTH, "Name cannot be longer then " + NAME_MAX_LENGTH + " characters");
        this.value = value;
    }

}