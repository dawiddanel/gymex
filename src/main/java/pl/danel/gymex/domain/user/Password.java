package pl.danel.gymex.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Password {

    public static final int PASSWORD_MAX_LENGTH = 255;

    @Getter
    @Column
    private String value;

    public static Password of(String value, PasswordEncoder encoder) {
        return new Password(value, encoder);
    }

    private Password(String value, PasswordEncoder encoder) {
        initValue(value, encoder);
    }

    private void initValue(String value, PasswordEncoder encoder) {
        DomainAsserts.assertArgumentNotTooLong(value, PASSWORD_MAX_LENGTH, "Username cannot be longer then " + PASSWORD_MAX_LENGTH + " characters");
        this.value = encoder.encode(value);
    }

}
