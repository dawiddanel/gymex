package pl.danel.gymex.domain.gym.assortment.definitions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import pl.danel.gymex.domain.common.BodyPart;
import pl.danel.gymex.domain.common.EquipmentType;
import pl.danel.gymex.domain.common.Purpose;
import pl.danel.gymex.infrastructure.converters.BodyPartListConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EQUIPMENT_DEFINITION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class EquipmentDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipment_def_sequence")
    @SequenceGenerator(name = "equipment_def_sequence", sequenceName = "SEQ_EQUIPMENT_DEF", allocationSize = 1)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Nullable
    private Double weight;

    @Nullable
    @Convert(converter = BodyPartListConverter.class)
    private List<BodyPart> aimedBodyParts = new ArrayList<>();
}
