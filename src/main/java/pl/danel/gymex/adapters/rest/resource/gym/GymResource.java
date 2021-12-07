package pl.danel.gymex.adapters.rest.resource.gym;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateGymCommand;
import pl.danel.gymex.adapters.rest.resource.gym.command.UpdateGymCommand;
import pl.danel.gymex.adapters.rest.resource.gym.presence.CreatePresenceCommand;
import pl.danel.gymex.application.gym.GymService;
import pl.danel.gymex.application.gym.dto.GymDto;
import pl.danel.gymex.application.gym.presence.PresenceDto;
import pl.danel.gymex.domain.gym.presence.Presence;

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
        return gymService.allGymsDto();
    }

    @GetMapping("/{id}/presence/current")
    public List<PresenceDto> allGymCurrentPresences(@PathVariable Long id) {
        return gymService.gymCurrentPresences(id);
    }

    @PostMapping("/{id}/presence")
    public void createPresence(@PathVariable Long id, @RequestBody CreatePresenceCommand command) {
        gymService.createPresence(id, command);
    }

    @PutMapping("/{id}/presence/{presenceId}/finish")
    public void finishPresence(@PathVariable Long id, @PathVariable Long presenceId) {
        gymService.finishMemberPresence(id, presenceId);
    }

}
