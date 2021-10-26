package pl.danel.gymex.application.gym.assortment.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.danel.gymex.adapters.rest.resource.gym.assortment.command.CreateEquipmentDefinitionCommand;
import pl.danel.gymex.domain.asserts.InvalidArgumentException;
import pl.danel.gymex.domain.common.BodyPart;
import pl.danel.gymex.domain.common.EquipmentType;
import pl.danel.gymex.domain.common.Purpose;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipmentDefinition;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssortmentCommandMapper {

    public CreateEquipmentDefinition createEquipmentDefinition(CreateEquipmentDefinitionCommand command) {
        return CreateEquipmentDefinition.builder()
                .name(command.getName())
                .description(command.getDescription())
                .type(mapEquipmentType(command.getType()))
                .purpose(mapPurpose(command.getPurpose()))
                .weight(command.getWeight())
                .aimedBodyParts(bodyParts(command.getAimedBodyParts()))
                .build();
    }

    private EquipmentType mapEquipmentType(String type) {
        return Arrays.stream(EquipmentType.values())
                .filter(r -> r.name().equals(type))
                .findAny()
                .orElseThrow(() -> new InvalidArgumentException("No such EQUIPMENT_TYPE parameter"));
    }

    private Purpose mapPurpose(String purpose) {
        return Arrays.stream(Purpose.values())
                .filter(r -> r.name().equals(purpose))
                .findAny()
                .orElseThrow(() -> new InvalidArgumentException("No such PURPOSE parameter"));
    }

    private List<BodyPart> bodyParts(List<String> bodyParts) {
        if (CollectionUtils.isEmpty(bodyParts)) {
            return null;
        }
        return bodyParts.stream()
                .map(this::mapBodyPart)
                .collect(Collectors.toList());
    }

    private BodyPart mapBodyPart(String part) {
        return Arrays.stream(BodyPart.values())
                .filter(r -> r.name().equals(part))
                .findAny()
                .orElseThrow(() -> new InvalidArgumentException("No such BODY_PART parameter"));
    }


}
