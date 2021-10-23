package pl.danel.gymex.adapters.rest.resource.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterCommand {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String role;
}
