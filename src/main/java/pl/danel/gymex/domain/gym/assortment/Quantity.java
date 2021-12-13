package pl.danel.gymex.domain.gym.assortment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.asserts.DomainAsserts;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class Quantity {

    public static final int QUANTITY_MIN_VALUE = 1;

    @Getter
    @Column
    private Integer value;

    public static Quantity of(Integer value) {
        return new Quantity(value);
    }

    private Quantity(Integer value) {
        initValue(value);
    }

    private void initValue(Integer value) {
        DomainAsserts.assertArgumentNotNull(value, "Quantity is null");
        DomainAsserts.assertState(value >= QUANTITY_MIN_VALUE, "quantity min value is 1");
        this.value = value;
    }

}
