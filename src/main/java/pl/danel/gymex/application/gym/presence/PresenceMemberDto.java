package pl.danel.gymex.application.gym.presence;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PresenceMemberDto {
    private Long id;
    private String firstName;
    private String lastName;
}
