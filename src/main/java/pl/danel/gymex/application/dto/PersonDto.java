package pl.danel.gymex.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;
}
