package pl.danel.gymex.adapters.rest.resource.gym;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateGymCommand;
import pl.danel.gymex.application.gym.GymService;
import pl.danel.gymex.application.gym.dto.GymDto;

import java.util.List;

@RestController
@RequestMapping(value = "gym", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GymResource {

    private final GymService gymService;

    @PostMapping("/create")
    public GymDto createGym(@RequestBody CreateGymCommand command) {
        return gymService.createGym(command);
    }

    @GetMapping("/all")
    public List<GymDto> allGyms() {
        return gymService.allGyms();
    }

}
