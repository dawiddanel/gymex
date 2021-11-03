package pl.danel.gymex.application.gym.timetable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.CreateActivityCommand;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.UpdateActivityCommand;
import pl.danel.gymex.application.gym.timetable.dto.ActivityDto;
import pl.danel.gymex.application.gym.timetable.dto.TimetableDto;
import pl.danel.gymex.application.gym.timetable.mapper.TimetableCommandMapper;
import pl.danel.gymex.application.gym.timetable.mapper.TimetableMapper;
import pl.danel.gymex.application.person.PersonService;
import pl.danel.gymex.domain.asserts.NotFoundException;
import pl.danel.gymex.domain.gym.timetable.Activity;
import pl.danel.gymex.domain.gym.timetable.Timetable;
import pl.danel.gymex.domain.gym.timetable.TimetableRepository;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinitionRepository;
import pl.danel.gymex.domain.gym.timetable.command.*;
import pl.danel.gymex.domain.person.TrainerRepository;
import pl.danel.gymex.domain.person.member.Member;
import pl.danel.gymex.domain.person.trainer.Trainer;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TimetableService {

    private final PersonService personService;

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

    @Transactional
    public ActivityDto joinActivity(Long timetableId, Long activityId) {
        Member member = personService.currentlyLoggedMember();

        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new NotFoundException("No such TIMETABLE found"));

        Activity activity = timetable.activityById(activityId);

        AddParticipant addParticipant = timetableCommandMapper.addParticipant(member);
        activity.addParticipant(addParticipant);

        timetable = timetableRepository.save(timetable);
        return timetableMapper.activity(timetable.activityById(activityId));
    }

    @Transactional
    public ActivityDto resignFromActivity(Long timetableId, Long activityId) {
        Member member = personService.currentlyLoggedMember();

        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new NotFoundException("No such TIMETABLE found"));

        Activity activity = timetable.activityById(activityId);

        RemoveParticipant removeParticipant = timetableCommandMapper.removeParticipant(member);
        activity.removeParticipant(removeParticipant);

        timetable = timetableRepository.save(timetable);
        return timetableMapper.activity(timetable.activityById(activityId));
    }

    @Transactional
    public ActivityDto confirmAttendance(Long timetableId, Long activityId, Long userId) {
        Member member = personService.memberById(userId);

        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new NotFoundException("No such TIMETABLE found"));

        Activity activity = timetable.activityById(activityId);

        ConfirmAttendance confirmAttendance = timetableCommandMapper.confirmAttendance(member);
        activity.confirmAttendance(confirmAttendance);

        timetable = timetableRepository.save(timetable);
        return timetableMapper.activity(timetable.activityById(activityId));
    }

    @Transactional
    public ActivityDto resignAttendance(Long timetableId, Long activityId, Long userId) {
        Member member = personService.memberById(userId);

        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new NotFoundException("No such TIMETABLE found"));

        Activity activity = timetable.activityById(activityId);

        ResignAttendance resignAttendance = timetableCommandMapper.resignAttendance(member);
        activity.resignAttendance(resignAttendance);

        timetable = timetableRepository.save(timetable);
        return timetableMapper.activity(timetable.activityById(activityId));
    }

}
