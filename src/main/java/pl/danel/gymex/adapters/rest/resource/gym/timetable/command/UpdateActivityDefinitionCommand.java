package pl.danel.gymex.adapters.rest.resource.gym.timetable.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateActivityDefinitionCommand {
    private String name;
    private String description;
    private String level;
}
