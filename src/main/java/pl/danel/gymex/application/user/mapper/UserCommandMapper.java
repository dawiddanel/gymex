package pl.danel.gymex.application.user.mapper;

import org.springframework.stereotype.Component;
import pl.danel.gymex.adapters.rest.resource.command.RegisterCommand;
import pl.danel.gymex.domain.person.user.command.CreateUserCommand;

@Component
public class UserCommandMapper {

    public CreateUserCommand createUserCommand(RegisterCommand command) {
        return CreateUserCommand.builder()
                .username(command.getUsername())
                .password(command.getPassword())
                .email(command.getEmail())
                .role(command.getRole())
                .build();
    }

}
