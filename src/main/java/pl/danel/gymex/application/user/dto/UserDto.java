package pl.danel.gymex.application.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String username;
    private String email;
}
