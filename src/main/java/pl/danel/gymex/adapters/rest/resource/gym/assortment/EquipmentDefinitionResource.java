package pl.danel.gymex.adapters.rest.resource.gym.assortment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.danel.gymex.adapters.rest.resource.gym.assortment.command.CreateEquipmentDefinitionCommand;
import pl.danel.gymex.adapters.rest.resource.gym.assortment.command.UpdateEquipmentDefinitionCommand;
import pl.danel.gymex.application.gym.assortment.EquipmentDefinitionService;
import pl.danel.gymex.application.gym.assortment.dto.EquipmentDefinitionDto;

import java.util.List;

@RestController
@RequestMapping(value = "assortment/equipment/definition", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EquipmentDefinitionResource {

    private final EquipmentDefinitionService equipmentDefinitionService;

    @PostMapping
    public EquipmentDefinitionDto createDefinition(@RequestBody CreateEquipmentDefinitionCommand command) {
        return equipmentDefinitionService.createEquipmentDefinition(command);
    }

    @PutMapping("/{id}")
    public EquipmentDefinitionDto updateDefinition(@PathVariable Long id, @RequestBody UpdateEquipmentDefinitionCommand command) {
        return equipmentDefinitionService.updateEquipmentDefinition(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteEquipmentDefinition(@PathVariable Long id) {
        equipmentDefinitionService.deleteEquipmentDefinition(id);
    }

    @GetMapping("/{id}")
    public EquipmentDefinitionDto definition(@PathVariable Long id) {
        return equipmentDefinitionService.definitionById(id);
    }

    @GetMapping("/all")
    public List<EquipmentDefinitionDto> allEquipmentDefinitions() {
        return equipmentDefinitionService.allDefinitions();
    }
}
