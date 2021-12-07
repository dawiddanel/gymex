package pl.danel.gymex.adapters.rest.resource.gym.presence;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePresenceCommand {
    private Long memberId;
}
