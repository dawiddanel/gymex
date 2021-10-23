package pl.danel.gymex.domain.user.command;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.domain.user.Role;

import java.time.LocalDate;

@Builder
@Getter
public class CreateUserCommand {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String username;
    private String password;
    private String email;
    private String role;
}
