package pl.danel.gymex.domain.gym.timetable.command;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinition;
import pl.danel.gymex.domain.person.trainer.Trainer;

import java.time.LocalDateTime;

@Builder
@Getter
public class UpdateActivity {
    private ActivityDefinition activityDefinition;
    private Trainer trainer;
    private LocalDateTime startTime;
    private Integer duration;
    private Integer capacity;
}
