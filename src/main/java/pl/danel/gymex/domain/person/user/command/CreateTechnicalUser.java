package pl.danel.gymex.domain.person.user.command;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.domain.person.user.Role;

@Builder
@Getter
public class CreateTechnicalUser {
    private String username;
    private String password;
    private String email;
    private CreatePerson person;
    private Role role;
}
