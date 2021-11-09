package pl.danel.gymex.adapters.rest.resource.gym;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateGymCommand;
import pl.danel.gymex.adapters.rest.resource.gym.command.UpdateGymCommand;
import pl.danel.gymex.application.gym.GymService;
import pl.danel.gymex.application.gym.dto.GymDto;

import java.util.List;

@RestController
@RequestMapping(value = "gym", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GymResource {

    private final GymService gymService;

    @PostMapping
    public GymDto createGym(@RequestBody CreateGymCommand command) {
        return gymService.createGym(command);
    }

    @PutMapping("/{id}")
    public GymDto updateGym(@PathVariable Long id, @RequestBody UpdateGymCommand command) {
        return gymService.updateGym(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteGym(@PathVariable Long id) {
        gymService.deleteGym(id);
    }

    @GetMapping("/{id}")
    public GymDto gymById(@PathVariable Long id) {
        return gymService.gymById(id);
    }

    @GetMapping("/all")
    public List<GymDto> allGyms() {
        return gymService.allGyms();
    }

}
