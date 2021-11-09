package pl.danel.gymex.adapters.rest.resource.gym.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateGymCommand {
    private String name;
    private Integer capacity;
    private CreateAddressCommand address;
    private LocalDate timetableStartDate;
}
