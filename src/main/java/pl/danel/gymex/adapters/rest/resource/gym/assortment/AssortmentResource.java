package pl.danel.gymex.adapters.rest.resource.gym.assortment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.danel.gymex.adapters.rest.resource.gym.assortment.command.CreateEquipmentCommand;
import pl.danel.gymex.adapters.rest.resource.gym.assortment.command.UpdateEquipmentCommand;
import pl.danel.gymex.application.gym.assortment.AssortmentService;
import pl.danel.gymex.application.gym.assortment.dto.AssortmentDto;
import pl.danel.gymex.application.gym.assortment.dto.EquipmentDto;

@RestController
@RequestMapping(value = "assortment", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AssortmentResource {

    private final AssortmentService assortmentService;

    @PostMapping("/{gymId}/equipment")
    public AssortmentDto createEquipment(@PathVariable Long gymId, @RequestBody CreateEquipmentCommand command) {
        return assortmentService.createEquipment(gymId, command);
    }

    @PutMapping("/{gymId}/equipment/{equipmentId}")
    public AssortmentDto updateEquipment(@PathVariable Long gymId, @PathVariable Long equipmentId, @RequestBody UpdateEquipmentCommand command) {
        return assortmentService.updateEquipment(gymId, equipmentId, command);
    }

    @DeleteMapping("/{gymId}/equipment/{equipmentId}")
    public AssortmentDto deleteEquipment(@PathVariable Long gymId, @PathVariable Long equipmentId) {
        return assortmentService.deleteEquipment(gymId, equipmentId);
    }

    @GetMapping("/{gymId}")
    public AssortmentDto getAssortment(@PathVariable Long gymId) {
        return assortmentService.getAssortment(gymId);
    }

    @GetMapping("/{gymId}/equipment/{equipmentId}")
    public EquipmentDto getEquipment(@PathVariable Long gymId, @PathVariable Long equipmentId) {
        return assortmentService.getEquipment(gymId, equipmentId);
    }

}
