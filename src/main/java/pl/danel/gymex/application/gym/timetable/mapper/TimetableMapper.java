package pl.danel.gymex.application.gym.timetable.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.danel.gymex.application.gym.timetable.dto.ActivityDefinitionDto;
import pl.danel.gymex.application.gym.timetable.dto.ActivityDto;
import pl.danel.gymex.application.gym.timetable.dto.AttendanceDto;
import pl.danel.gymex.application.gym.timetable.dto.TimetableDto;
import pl.danel.gymex.application.person.mapper.PersonMapper;
import pl.danel.gymex.domain.common.ActivityStatus;
import pl.danel.gymex.domain.common.Level;
import pl.danel.gymex.domain.gym.timetable.Timetable;
import pl.danel.gymex.domain.gym.timetable.Activity;
import pl.danel.gymex.domain.gym.timetable.activities.Attendance;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TimetableMapper {

    private final PersonMapper personMapper;

    public TimetableDto timetable(Timetable timetable) {
        return TimetableDto.builder()
                .id(timetable.getId())
                .activities(activities(timetable.getActivities()))
                .updateDate(timetable.getUpdateDate())
                .startDate(timetable.getStartDate())
                .endDate(timetable.getEndDate())
                .orderNumber(timetable.getOrderNumber())
                .build();
    }

    public List<ActivityDto> activities(List<Activity> activities) {
        if (CollectionUtils.isEmpty(activities)) {
            return Collections.emptyList();
        }
        return activities.stream()
                .map(this::activity)
                .sorted(Comparator.comparing(ActivityDto::getStartTime, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    public ActivityDto activity(Activity activity) {
        return ActivityDto.builder()
                .id(activity.getId())
                .definition(activityDefinition(activity.getDefinition()))
                .trainer(personMapper.trainer(activity.getTrainer()))
                .participants(personMapper.members(activity.getParticipants()))
                .attendance(attendances(activity.getAttendance()))
                .startTime(activity.getStartTime())
                .endTime(activity.getEndTime())
                .capacity(activity.getCapacity())
                .status(mapStatus(activity.getStatus()))
                .build();
    }

    public ActivityDefinitionDto activityDefinition(ActivityDefinition activityDefinition) {
        return ActivityDefinitionDto.builder()
                .id(activityDefinition.getId())
                .name(activityDefinition.getName() != null ? activityDefinition.getName().getValue() : null)
                .description(activityDefinition.getDescription() != null ? activityDefinition.getDescription().getValue() : null)
                .level(mapLevel(activityDefinition.getLevel()))
                .build();
    }

    public List<AttendanceDto> attendances(List<Attendance> attendances) {
        if (CollectionUtils.isEmpty(attendances)) {
            return Collections.emptyList();
        }
        return attendances.stream()
                .map(this::attendance)
                .collect(Collectors.toList());
    }

    public AttendanceDto attendance(Attendance attendance) {
        return AttendanceDto.builder()
                .member(personMapper.member(attendance.getMember()))
                .attended(attendance.getAttended())
                .build();
    }

    private String mapLevel(Level level) {
        if (level == null) {
            return null;
        }
        return level.name();
    }

    private String mapStatus(ActivityStatus status) {
        if (status == null) {
            return null;
        }
        return status.name();
    }
}
