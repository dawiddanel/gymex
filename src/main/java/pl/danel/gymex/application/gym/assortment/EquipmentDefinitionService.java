package pl.danel.gymex.application.gym.assortment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.gym.assortment.command.CreateEquipmentDefinitionCommand;
import pl.danel.gymex.adapters.rest.resource.gym.assortment.command.UpdateEquipmentDefinitionCommand;
import pl.danel.gymex.application.gym.assortment.dto.EquipmentDefinitionDto;
import pl.danel.gymex.application.gym.assortment.mapper.AssortmentCommandMapper;
import pl.danel.gymex.application.gym.assortment.mapper.AssortmentMapper;
import pl.danel.gymex.domain.asserts.NotFoundException;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipmentDefinition;
import pl.danel.gymex.domain.gym.assortment.command.UpdateEquipmentDefinition;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinition;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinitionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EquipmentDefinitionService {

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

    @Transactional
    public EquipmentDefinitionDto updateEquipmentDefinition(Long id, UpdateEquipmentDefinitionCommand command) {
        EquipmentDefinition definition = equipmentDefinitionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No such DEFINITION found"));

        UpdateEquipmentDefinition updateEquipmentDefinition = assortmentCommandMapper.updateEquipmentDefinition(command);
        definition.update(updateEquipmentDefinition);

        definition = equipmentDefinitionRepository.save(definition);
        return assortmentMapper.equipmentDefinition(definition);
    }

    @Transactional
    public void deleteEquipmentDefinition(Long id) {
        EquipmentDefinition definition = equipmentDefinitionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No such DEFINITION found"));

        equipmentDefinitionRepository.delete(definition);
    }

    public EquipmentDefinitionDto definitionById(Long id) {
        return equipmentDefinitionRepository.findById(id)
                .map(assortmentMapper::equipmentDefinition)
                .orElseThrow(() -> new NotFoundException("No such DEFINITION found"));
    }

    public List<EquipmentDefinitionDto> allDefinitions() {
        return equipmentDefinitionRepository.findAll().stream()
                .map(assortmentMapper::equipmentDefinition)
                .collect(Collectors.toList());
    }

}
