package pl.danel.gymex.application.person.dto;

import lombok.Getter;
import lombok.Setter;
import pl.danel.gymex.application.gym.presence.PresenceDto;

import java.util.List;

@Getter
@Setter
public class MemberDto extends PersonDto {
    private PassDto pass;
    private List<PresenceDto> presences;
}
