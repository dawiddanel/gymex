package pl.danel.gymex.application.gym.timetable.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ActivityDefinitionDto {
    private Long id;
    private String name;
    private String description;
    private String level;
}
