package pl.danel.gymex.domain.gym.timetable.command;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.domain.common.Level;

@Builder
@Getter
public class UpdateActivityDefinition {
    private String name;
    private String description;
    private Level level;
}
