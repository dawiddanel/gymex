package pl.danel.gymex.adapters.rest.resource.gym.assortment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.danel.gymex.adapters.rest.resource.gym.assortment.command.CreateEquipmentDefinitionCommand;
import pl.danel.gymex.application.gym.assortment.AssortmentService;
import pl.danel.gymex.application.gym.assortment.dto.EquipmentDefinitionDto;

import java.util.List;

@RestController
@RequestMapping(value = "assortment/equipment", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EquipmentResource {

    private final AssortmentService assortmentService;

    @PostMapping("/definition/create")
    public EquipmentDefinitionDto createDefinition(@RequestBody CreateEquipmentDefinitionCommand command) {
        return assortmentService.createEquipmentDefinition(command);
    }

    @GetMapping("/definiton/all")
    public List<EquipmentDefinitionDto> allEquipmentDefinitions() {
        return assortmentService.allDefinitions();
    }
}
