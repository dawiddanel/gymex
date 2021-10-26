package pl.danel.gymex.adapters.rest.resource.gym.command;

import lombok.Getter;
import lombok.Setter;
import pl.danel.gymex.adapters.rest.resource.common.command.CreateAddressCommand;

@Getter
@Setter
public class CreateGymCommand {
    private String name;
    private Integer squareMeters;
    private CreateAddressCommand address;
}
