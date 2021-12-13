package pl.danel.gymex.domain.person.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Email {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Getter
    @Column
    private String value;

    public static Email of(String value) {
        return new Email(value);
    }

    private Email(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "Email is null");
        DomainAsserts.assertArgumentMatchesPattern(EMAIL_REGEX, value, "Email is malmorfed");
        this.value = value;
    }

}
