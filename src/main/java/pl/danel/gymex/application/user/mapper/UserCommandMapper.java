package pl.danel.gymex.application.user.mapper;

import org.springframework.stereotype.Component;
import pl.danel.gymex.adapters.rest.resource.security.command.RegisterCommand;
import pl.danel.gymex.adapters.rest.resource.user.command.CreateTechnicalUserCommand;
import pl.danel.gymex.domain.asserts.InvalidArgumentException;
import pl.danel.gymex.domain.person.user.Role;
import pl.danel.gymex.domain.person.user.command.CreatePerson;
import pl.danel.gymex.domain.person.user.command.CreateTechnicalUser;
import pl.danel.gymex.domain.person.user.command.CreateUser;

import java.util.Arrays;

@Component
public class UserCommandMapper {

    public CreateUser createUserCommand(RegisterCommand command) {
        return CreateUser.builder()
                .username(command.getUsername())
                .password(command.getPassword())
                .email(command.getEmail())
                .person(createPerson(command))
                .build();
    }

    public CreateTechnicalUser createTechnicalUser(CreateTechnicalUserCommand command) {
        return CreateTechnicalUser.builder()
                .username(command.getUsername())
                .password(command.getPassword())
                .email(command.getEmail())
                .person(createPerson(command))
                .role(mapRole(command.getRole()))
                .build();
    }

    private CreatePerson createPerson(RegisterCommand command) {
        return CreatePerson.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .pesel(command.getPesel())
                .birthDate(command.getBirthDate())
                .build();
    }

    private CreatePerson createPerson(CreateTechnicalUserCommand command) {
        return CreatePerson.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .pesel(command.getPesel())
                .birthDate(command.getBirthDate())
                .build();
    }

    private Role mapRole(String role) {
        return Arrays.stream(Role.values())
                .filter(r -> r.name().equals(role))
                .findAny()
                .orElseThrow(() -> new InvalidArgumentException("No such ROLE parameter"));
    }

}
