package pl.danel.gymex.domain.gym.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateGym {
    private String name;
    private Integer squareMeters;
    private CreateAddress address;
}
