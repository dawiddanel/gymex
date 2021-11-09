package pl.danel.gymex.adapters.rest.resource.person.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreatePassCommand {
    private LocalDate startDate;
    private LocalDate endDate;
}
