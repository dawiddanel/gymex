package pl.danel.gymex.adapters.rest.resource.gym.timetable.command;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateActivityCommand {
    private Long definitionId;
    private Long trainerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer capacity;
}
