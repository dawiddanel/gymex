package pl.danel.gymex.domain.gym.assortment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.danel.gymex.domain.asserts.DomainAsserts;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipment;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinition;

import javax.persistence.*;

@Entity
@Table(name = "EQUIPMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipment_sequence")
    @SequenceGenerator(name = "equipment_sequence", sequenceName = "SEQ_EQUIPMENT", allocationSize = 1, initialValue = 100)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Assortment assortment;

    @ManyToOne
    private EquipmentDefinition definition;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "QUANTITY"))
    private Quantity quantity;

    private Equipment(CreateEquipment command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        DomainAsserts.assertArgumentNotNull(command.getDefinition(), "definition cannot be null");
        this.definition = command.getDefinition();
        this.quantity = Quantity.of(command.getQuantity());
    }

    public static Equipment create(CreateEquipment command) {
        return new Equipment(command);
    }

    public void updateQuantity(Integer quantity) {
        this.quantity = Quantity.of(quantity);
    }

}
