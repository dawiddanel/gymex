package pl.danel.gymex.application.gym.timetable.mapper;

import org.springframework.stereotype.Component;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.CreateActivityCommand;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.CreateActivityDefinitionCommand;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.UpdateActivityCommand;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.UpdateActivityDefinitionCommand;
import pl.danel.gymex.domain.asserts.InvalidArgumentException;
import pl.danel.gymex.domain.common.Level;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.command.CreateActivity;
import pl.danel.gymex.domain.gym.timetable.command.CreateActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.command.UpdateActivity;
import pl.danel.gymex.domain.gym.timetable.command.UpdateActivityDefinition;
import pl.danel.gymex.domain.person.trainer.Trainer;

import java.util.Arrays;

@Component
public class TimetableCommandMapper {

    public CreateActivity createActivity(CreateActivityCommand command, ActivityDefinition definition, Trainer trainer) {
        return CreateActivity.builder()
                .activityDefinition(definition)
                .trainer(trainer)
                .startTime(command.getStartTime())
                .duration(command.getDuration())
                .capacity(command.getCapacity())
                .build();
    }

    public UpdateActivity updateActivity(UpdateActivityCommand command, ActivityDefinition definition, Trainer trainer) {
        return UpdateActivity.builder()
                .activityDefinition(definition)
                .trainer(trainer)
                .startTime(command.getStartTime())
                .duration(command.getDuration())
                .capacity(command.getCapacity())
                .build();
    }

    public CreateActivityDefinition createActivityDefinition(CreateActivityDefinitionCommand command) {
        return CreateActivityDefinition.builder()
                .name(command.getName())
                .description(command.getDescription())
                .level(mapLevel(command.getLevel()))
                .build();
    }

    public UpdateActivityDefinition updateActivityDefinition(UpdateActivityDefinitionCommand command) {
        return UpdateActivityDefinition.builder()
                .name(command.getName())
                .description(command.getDescription())
                .level(mapLevel(command.getLevel()))
                .build();
    }

    private Level mapLevel(String level) {
        return Arrays.stream(Level.values())
                .filter(r -> r.name().equals(level))
                .findAny()
                .orElseThrow(() -> new InvalidArgumentException("No such LEVEL parameter"));
    }
}
