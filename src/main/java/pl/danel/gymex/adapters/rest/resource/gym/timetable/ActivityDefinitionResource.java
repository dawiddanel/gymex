package pl.danel.gymex.adapters.rest.resource.gym.timetable;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.CreateActivityDefinitionCommand;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.UpdateActivityDefinitionCommand;
import pl.danel.gymex.application.gym.timetable.ActivityDefinitionService;
import pl.danel.gymex.application.gym.timetable.dto.ActivityDefinitionDto;

import java.util.List;

@RestController
@RequestMapping(value = "activity/definition", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ActivityDefinitionResource {

    private final ActivityDefinitionService activityDefinitionService;

    @PostMapping
    public ActivityDefinitionDto createDefinition(@RequestBody CreateActivityDefinitionCommand command) {
        return activityDefinitionService.createActivityDefinition(command);
    }

    @PutMapping("/{id}")
    public ActivityDefinitionDto updateDefinition(@PathVariable Long id, @RequestBody UpdateActivityDefinitionCommand command) {
        return activityDefinitionService.updateActivityDefinition(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteActivityDefinition(@PathVariable Long id) {
        activityDefinitionService.deleteActivityDefinition(id);
    }

    @GetMapping("/{id}")
    public ActivityDefinitionDto definition(@PathVariable Long id) {
        return activityDefinitionService.definitionById(id);
    }

    @GetMapping("/all")
    public List<ActivityDefinitionDto> allActivityDefinitions() {
        return activityDefinitionService.allDefinitions();
    }

}
