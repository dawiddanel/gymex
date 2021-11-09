package pl.danel.gymex.adapters.rest.resource.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.danel.gymex.adapters.rest.resource.user.command.CreateTechnicalUserCommand;
import pl.danel.gymex.application.user.UserService;
import pl.danel.gymex.application.user.dto.UserDto;

@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @PostMapping("/technical")
    public UserDto createTechnicalUser(@RequestBody CreateTechnicalUserCommand command) {
        return userService.createTechnicalUser(command);
    }

}
