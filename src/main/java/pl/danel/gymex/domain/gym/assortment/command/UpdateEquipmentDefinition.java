package pl.danel.gymex.domain.gym.assortment.command;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.domain.common.BodyPart;
import pl.danel.gymex.domain.common.EquipmentType;
import pl.danel.gymex.domain.common.Purpose;

import java.util.List;

@Builder
@Getter
public class UpdateEquipmentDefinition {
    private String name;
    private String description;
    private EquipmentType type;
    private Purpose purpose;
    private Double weight;
    private List<BodyPart> aimedBodyParts;
}
