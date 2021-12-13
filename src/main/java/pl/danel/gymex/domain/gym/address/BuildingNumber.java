package pl.danel.gymex.domain.gym.address;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class BuildingNumber {

    public static final int BUILDING_NUMBER_MAX_LENGTH = 50;

    @Getter
    @Column
    private String value;

    public static BuildingNumber of(String value) {
        return new BuildingNumber(value);
    }

    private BuildingNumber(String value) {
        initValue(value);
    }

    private void initValue(String value) {
        DomainAsserts.assertArgumentNotNull(value, "Building number is null");
        DomainAsserts.assertArgumentNotTooLong(value, BUILDING_NUMBER_MAX_LENGTH, "Building number cannot be longer then " + BUILDING_NUMBER_MAX_LENGTH + " characters");
        this.value = value;
    }

}
