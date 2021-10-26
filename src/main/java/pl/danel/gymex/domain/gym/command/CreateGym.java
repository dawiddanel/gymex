package pl.danel.gymex.domain.gym.command;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.domain.common.command.CreateAddress;

@Builder
@Getter
public class CreateGym {
    private String name;
    private Integer squareMeters;
    private CreateAddress addressCommand;
}
