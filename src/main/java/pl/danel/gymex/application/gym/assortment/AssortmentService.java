package pl.danel.gymex.application.gym.assortment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.gym.assortment.command.CreateEquipmentCommand;
import pl.danel.gymex.adapters.rest.resource.gym.assortment.command.UpdateEquipmentCommand;
import pl.danel.gymex.application.gym.assortment.dto.AssortmentDto;
import pl.danel.gymex.application.gym.assortment.mapper.AssortmentCommandMapper;
import pl.danel.gymex.application.gym.assortment.mapper.AssortmentMapper;
import pl.danel.gymex.domain.asserts.NotFoundException;
import pl.danel.gymex.domain.gym.assortment.Assortment;
import pl.danel.gymex.domain.gym.assortment.AssortmentRepository;
import pl.danel.gymex.domain.gym.assortment.Equipment;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipment;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinition;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinitionRepository;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssortmentService {

    private final AssortmentRepository assortmentRepository;
    private final EquipmentDefinitionRepository equipmentDefinitionRepository;

    private final AssortmentMapper assortmentMapper;
    private final AssortmentCommandMapper assortmentCommandMapper;

    public AssortmentDto getAssortment(Long assortmentId) {
        Assortment assortment = assortmentRepository.findById(assortmentId)
                .orElseThrow(() -> new NotFoundException("No such ASSORTMENT found"));

        return assortmentMapper.assortment(assortment);
    }

    @Transactional
    public AssortmentDto createEquipment(Long assortmentId, CreateEquipmentCommand command) {
        Assortment assortment = assortmentRepository.findById(assortmentId)
                .orElseThrow(() -> new NotFoundException("No such ASSORTMENT found"));

        EquipmentDefinition definition = equipmentDefinitionRepository.findById(command.getDefinitionId())
                .orElseThrow(() -> new NotFoundException("No such EQUIPMENT_DEFINITION found"));

        CreateEquipment createEquipment = assortmentCommandMapper.createEquipment(command, definition);
        Equipment equipment = Equipment.create(createEquipment);
        assortment.addEquipment(equipment);

        assortment = assortmentRepository.save(assortment);
        return assortmentMapper.assortment(assortment);
    }

    @Transactional
    public AssortmentDto updateEquipment(Long assortmentId, Long equipmentId, UpdateEquipmentCommand command) {
        Assortment assortment = assortmentRepository.findById(assortmentId)
                .orElseThrow(() -> new NotFoundException("No such ASSORTMENT found"));

        Equipment equipment = assortment.equipmentById(equipmentId);
        equipment.updateQuantity(command.getQuantity());

        assortment = assortmentRepository.save(assortment);
        return assortmentMapper.assortment(assortment);
    }

    @Transactional
    public AssortmentDto deleteEquipment(Long assortmentId, Long equipmentId) {
        Assortment assortment = assortmentRepository.findById(assortmentId)
                .orElseThrow(() -> new NotFoundException("No such ASSORTMENT found"));

        Equipment equipment = assortment.equipmentById(equipmentId);
        assortment.removeEquipment(equipment);

        assortment = assortmentRepository.save(assortment);
        return assortmentMapper.assortment(assortment);
    }


}
