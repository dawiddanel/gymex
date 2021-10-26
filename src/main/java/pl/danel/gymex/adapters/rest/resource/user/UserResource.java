package pl.danel.gymex.adapters.rest.resource.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateGymCommand;
import pl.danel.gymex.adapters.rest.resource.user.command.CreateTechnicalUserCommand;
import pl.danel.gymex.application.gym.GymService;
import pl.danel.gymex.application.gym.dto.GymDto;
import pl.danel.gymex.application.user.UserService;
import pl.danel.gymex.application.user.dto.UserDto;

import java.util.List;

@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping("/technical/create")
    public UserDto createTechnicalUser(@RequestBody CreateTechnicalUserCommand command) {
        return userService.createTechnicalUser(command);
    }

}
