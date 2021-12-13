package pl.danel.gymex.domain.gym.address;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class PostalCode {

    private static final String POSTAL_CODE_REGEX = "\\d{2}-\\d{3}";

    @Getter
    @Column
    private String value;

    public static PostalCode of(String value) {
        return new PostalCode(value);
    }

    private PostalCode(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "Postal code is null");
        DomainAsserts.assertArgumentMatchesPattern(POSTAL_CODE_REGEX, value, "Postal code is malmorfed");
        this.value = value;
    }

}
