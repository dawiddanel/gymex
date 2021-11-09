package pl.danel.gymex.domain.person.user.command;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class CreatePerson {
    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;
}
