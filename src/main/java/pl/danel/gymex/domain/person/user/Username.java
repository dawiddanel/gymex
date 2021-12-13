package pl.danel.gymex.domain.person.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Username {

    public static final int USERNAME_MAX_LENGTH = 50;

    @Getter
    @Column
    private String value;

    public static Username of(String value) {
        return new Username(value);
    }

    private Username(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "Username is null");
        DomainAsserts.assertArgumentNotTooLong(value, USERNAME_MAX_LENGTH, "Username cannot be longer then " + USERNAME_MAX_LENGTH + " characters");
        this.value = value;
    }

}

