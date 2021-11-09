package pl.danel.gymex.adapters.rest.resource.security.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterCommand {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;
}
