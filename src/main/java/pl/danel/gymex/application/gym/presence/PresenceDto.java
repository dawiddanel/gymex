package pl.danel.gymex.application.gym.presence;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PresenceDto {
    private Long id;
    private PresenceMemberDto member;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
