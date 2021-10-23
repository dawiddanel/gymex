package pl.danel.gymex.adapters.rest.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.danel.gymex.adapters.rest.resource.command.RegisterCommand;
import pl.danel.gymex.adapters.rest.resource.command.SignInCommand;
import pl.danel.gymex.adapters.rest.resource.response.AuthenticationResponse;
import pl.danel.gymex.application.security.SecurityService;
import pl.danel.gymex.application.user.UserService;
import pl.danel.gymex.application.user.dto.UserDto;

@RestController
@RequestMapping(value = "security", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SecurityResource {

    private final SecurityService securityService;
    private final UserService userService;

    @PostMapping(value = "/authenticate")
    public AuthenticationResponse authenticate(@RequestBody SignInCommand command) throws Exception {
        String token = securityService.authenticate(command);
        return new AuthenticationResponse(token);
    }

    @PostMapping(value = "/register")
    public UserDto signIn(@RequestBody RegisterCommand command) {
        return userService.createUser(command);
    }

}
