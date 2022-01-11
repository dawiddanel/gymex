package pl.danel.gymex.domain.gym.assortment.definitions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import pl.danel.gymex.domain.asserts.DomainAsserts;
import pl.danel.gymex.domain.common.*;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipmentDefinition;
import pl.danel.gymex.domain.gym.assortment.command.UpdateEquipmentDefinition;
import pl.danel.gymex.infrastructure.converters.BodyPartListConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EQUIPMENT_DEFINITION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class EquipmentDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipment_def_sequence")
    @SequenceGenerator(name = "equipment_def_sequence", sequenceName = "SEQ_EQUIPMENT_DEF", allocationSize = 1, initialValue = 100)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "NAME"))
    private Name name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "DESCRIPTION"))
    private Description description;

    @Enumerated(EnumType.STRING)
    private EquipmentType type;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Nullable
    private Double weight;

    @Nullable
    @Convert(converter = BodyPartListConverter.class)
    private List<BodyPart> aimedBodyParts = new ArrayList<>();

    private EquipmentDefinition(CreateEquipmentDefinition command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        this.name = Name.of(command.getName());
        this.description = Description.of(command.getDescription());
        this.type = command.getType();
        this.purpose = command.getPurpose();
        this.weight = command.getWeight();
        this.aimedBodyParts = command.getAimedBodyParts();
    }

    public static EquipmentDefinition create(CreateEquipmentDefinition command) {
        return new EquipmentDefinition(command);
    }

    public void update(UpdateEquipmentDefinition command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        this.name = Name.of(command.getName());
        this.description = Description.of(command.getDescription());
        this.type = command.getType();
        this.purpose = command.getPurpose();
        this.weight = command.getWeight();
        this.aimedBodyParts = command.getAimedBodyParts();
    }
}
