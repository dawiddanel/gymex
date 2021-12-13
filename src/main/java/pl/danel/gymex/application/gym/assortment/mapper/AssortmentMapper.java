package pl.danel.gymex.application.gym.assortment.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.danel.gymex.application.gym.assortment.dto.AssortmentDto;
import pl.danel.gymex.application.gym.assortment.dto.EquipmentDefinitionDto;
import pl.danel.gymex.application.gym.assortment.dto.EquipmentDto;
import pl.danel.gymex.domain.common.BodyPart;
import pl.danel.gymex.domain.common.EquipmentType;
import pl.danel.gymex.domain.common.Purpose;
import pl.danel.gymex.domain.gym.assortment.Assortment;
import pl.danel.gymex.domain.gym.assortment.Equipment;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinition;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssortmentMapper {

    public AssortmentDto assortment(Assortment assortment) {
        return AssortmentDto.builder()
                .id(assortment.getId())
                .updateDate(assortment.getUpdateDate())
                .equipment(equipmentList(assortment.getEquipments()))
                .build();
    }

    public List<EquipmentDto> equipmentList(List<Equipment> equipment) {
        if (CollectionUtils.isEmpty(equipment)) {
            return null;
        }
        return equipment.stream()
                .map(this::equipment)
                .collect(Collectors.toList());
    }

    public EquipmentDto equipment(Equipment equipment) {
        return EquipmentDto.builder()
                .id(equipment.getId())
                .quantity(equipment.getQuantity() != null ? equipment.getQuantity().getValue() : null)
                .definition(equipmentDefinition(equipment.getDefinition()))
                .build();
    }

    public EquipmentDefinitionDto equipmentDefinition(EquipmentDefinition equipmentDefinition) {
        return EquipmentDefinitionDto.builder()
                .id(equipmentDefinition.getId())
                .name(equipmentDefinition.getName() != null ? equipmentDefinition.getName().getValue() : null)
                .description(equipmentDefinition.getDescription() != null ? equipmentDefinition.getDescription().getValue() : null)
                .type(mapEquipmentType(equipmentDefinition.getType()))
                .purpose(mapPurpose(equipmentDefinition.getPurpose()))
                .weight(equipmentDefinition.getWeight())
                .aimedBodyParts(aimedBodyParts(equipmentDefinition.getAimedBodyParts()))
                .build();
    }

    private String mapEquipmentType(EquipmentType type) {
        if (type == null) {
            return null;
        }
        return type.name();
    }

    private String mapPurpose(Purpose purpose) {
        if (purpose == null) {
            return null;
        }
        return purpose.name();
    }

    private List<String> aimedBodyParts(List<BodyPart> bodyParts) {
        if (CollectionUtils.isEmpty(bodyParts)) {
            return null;
        }
        return bodyParts.stream()
                .map(this::mapBodyPart)
                .collect(Collectors.toList());
    }

    private String mapBodyPart(BodyPart bodyPart) {
        if (bodyPart == null) {
            return null;
        }
        return bodyPart.name();
    }


}
