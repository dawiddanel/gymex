package pl.danel.gymex.application.gym.assortment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.gym.assortment.command.CreateEquipmentDefinitionCommand;
import pl.danel.gymex.application.gym.assortment.dto.EquipmentDefinitionDto;
import pl.danel.gymex.application.gym.assortment.mapper.AssortmentCommandMapper;
import pl.danel.gymex.application.gym.assortment.mapper.AssortmentMapper;
import pl.danel.gymex.domain.gym.GymRepository;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipmentDefinition;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinition;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinitionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssortmentService {

    private final GymRepository gymRepository;
    private final EquipmentDefinitionRepository equipmentDefinitionRepository;

    private final AssortmentMapper assortmentMapper;
    private final AssortmentCommandMapper assortmentCommandMapper;

    @Transactional
    public EquipmentDefinitionDto createEquipmentDefinition(CreateEquipmentDefinitionCommand command) {
        CreateEquipmentDefinition createEquipmentDefinition = assortmentCommandMapper.createEquipmentDefinition(command);
        EquipmentDefinition equipmentDefinition = EquipmentDefinition.create(createEquipmentDefinition);
        equipmentDefinition = equipmentDefinitionRepository.save(equipmentDefinition);
        return assortmentMapper.equipmentDefinition(equipmentDefinition);
    }

    public List<EquipmentDefinitionDto> allDefinitions() {
        return equipmentDefinitionRepository.findAll().stream()
                .map(assortmentMapper::equipmentDefinition)
                .collect(Collectors.toList());
    }

}
