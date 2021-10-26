package pl.danel.gymex.adapters.rest.resource.gym.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateGymCommand {
    private String name;
    private Integer squareMeters;
    private CreateAddressCommand address;
}
