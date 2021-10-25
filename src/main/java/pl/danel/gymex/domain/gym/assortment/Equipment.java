package pl.danel.gymex.domain.gym.assortment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinition;

import javax.persistence.*;

@Entity
@Table(name = "EQUIPMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipment_sequence")
    @SequenceGenerator(name = "equipment_sequence", sequenceName = "SEQ_EQUIPMENT", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Assortment assortment;

    @ManyToOne
    private EquipmentDefinition definition;

    private Integer quantity;

}
