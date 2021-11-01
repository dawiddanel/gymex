package pl.danel.gymex.application.gym.timetable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.CreateActivityCommand;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.UpdateActivityCommand;
import pl.danel.gymex.application.gym.timetable.dto.TimetableDto;
import pl.danel.gymex.application.gym.timetable.mapper.TimetableCommandMapper;
import pl.danel.gymex.application.gym.timetable.mapper.TimetableMapper;
import pl.danel.gymex.domain.asserts.NotFoundException;
import pl.danel.gymex.domain.gym.timetable.Activity;
import pl.danel.gymex.domain.gym.timetable.Timetable;
import pl.danel.gymex.domain.gym.timetable.TimetableRepository;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinitionRepository;
import pl.danel.gymex.domain.gym.timetable.command.CreateActivity;
import pl.danel.gymex.domain.gym.timetable.command.UpdateActivity;
import pl.danel.gymex.domain.person.TrainerRepository;
import pl.danel.gymex.domain.person.trainer.Trainer;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final ActivityDefinitionRepository activityDefinitionRepository;
    private final TrainerRepository trainerRepository;

    private final TimetableMapper timetableMapper;
    private final TimetableCommandMapper timetableCommandMapper;

    public TimetableDto getTimetable(Long timetableId) {
        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new NotFoundException("No such TIMETABLE found"));
        return timetableMapper.timetable(timetable);
    }

    @Transactional
    public TimetableDto createActivity(Long timetableId, CreateActivityCommand command) {
        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new NotFoundException("No such TIMETABLE found"));

        ActivityDefinition definition = activityDefinitionRepository.findById(command.getDefinitionId())
                .orElseThrow(() -> new NotFoundException("No such ACTIVITY_DEFINITION found"));

        Trainer trainer = trainerRepository.findById(command.getTrainerId())
                .orElseThrow(() -> new NotFoundException("No such TRAINER found"));

        CreateActivity createActivity = timetableCommandMapper.createActivity(command, definition, trainer);
        Activity activity = Activity.create(createActivity);
        timetable.addActivity(activity);

        timetable = timetableRepository.save(timetable);
        return timetableMapper.timetable(timetable);
    }

    @Transactional
    public TimetableDto updateActivity(Long timetableId, Long activityId, UpdateActivityCommand command) {
        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new NotFoundException("No such TIMETABLE found"));

        ActivityDefinition definition = activityDefinitionRepository.findById(command.getDefinitionId())
                .orElseThrow(() -> new NotFoundException("No such ACTIVITY_DEFINITION found"));

        Trainer trainer = trainerRepository.findById(command.getTrainerId())
                .orElseThrow(() -> new NotFoundException("No such TRAINER found"));

        UpdateActivity updateActivity = timetableCommandMapper.updateActivity(command, definition, trainer);
        Activity activity = timetable.activityById(activityId);
        activity.update(updateActivity);

        timetable = timetableRepository.save(timetable);
        return timetableMapper.timetable(timetable);
    }

    @Transactional
    public TimetableDto deleteActivity(Long timetableId, Long activityId) {
        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new NotFoundException("No such TIMETABLE found"));

        Activity activity = timetable.activityById(activityId);
        timetable.removeActivity(activity);

        timetable = timetableRepository.save(timetable);
        return timetableMapper.timetable(timetable);
    }

}
