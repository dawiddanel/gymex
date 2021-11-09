package pl.danel.gymex.adapters.rest.resource.user.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateTechnicalUserCommand {
    private String username;
    private String password;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;
}
