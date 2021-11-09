package pl.danel.gymex.application.gym.timetable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.CreateActivityDefinitionCommand;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.UpdateActivityDefinitionCommand;
import pl.danel.gymex.application.gym.timetable.dto.ActivityDefinitionDto;
import pl.danel.gymex.application.gym.timetable.mapper.TimetableCommandMapper;
import pl.danel.gymex.application.gym.timetable.mapper.TimetableMapper;
import pl.danel.gymex.domain.asserts.NotFoundException;

import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinitionRepository;
import pl.danel.gymex.domain.gym.timetable.command.CreateActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.command.UpdateActivityDefinition;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityDefinitionService {

    private final ActivityDefinitionRepository activityDefinitionRepository;

    private final TimetableMapper timetableMapper;
    private final TimetableCommandMapper timetableCommandMapper;

    @Transactional
    public ActivityDefinitionDto createActivityDefinition(CreateActivityDefinitionCommand command) {
        CreateActivityDefinition createActivityDefinition = timetableCommandMapper.createActivityDefinition(command);
        ActivityDefinition activityDefinition = ActivityDefinition.create(createActivityDefinition);
        activityDefinition = activityDefinitionRepository.save(activityDefinition);
        return timetableMapper.activityDefinition(activityDefinition);
    }

    @Transactional
    public ActivityDefinitionDto updateActivityDefinition(Long id, UpdateActivityDefinitionCommand command) {
        ActivityDefinition definition = activityDefinitionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No such DEFINITION found"));

        UpdateActivityDefinition updateActivityDefinition = timetableCommandMapper.updateActivityDefinition(command);
        definition.update(updateActivityDefinition);

        definition = activityDefinitionRepository.save(definition);
        return timetableMapper.activityDefinition(definition);
    }

    @Transactional
    public void deleteActivityDefinition(Long id) {
        ActivityDefinition definition = activityDefinitionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No such DEFINITION found"));

        activityDefinitionRepository.delete(definition);
    }

    public ActivityDefinitionDto definitionById(Long id) {
        return activityDefinitionRepository.findById(id)
                .map(timetableMapper::activityDefinition)
                .orElseThrow(() -> new NotFoundException("No such DEFINITION found"));
    }

    public List<ActivityDefinitionDto> allDefinitions() {
        return activityDefinitionRepository.findAll().stream()
                .map(timetableMapper::activityDefinition)
                .collect(Collectors.toList());
    }

}
