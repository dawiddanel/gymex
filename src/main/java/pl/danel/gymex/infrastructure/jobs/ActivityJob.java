package pl.danel.gymex.infrastructure.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.danel.gymex.application.gym.timetable.TimetableService;
import pl.danel.gymex.domain.common.ActivityStatus;
import pl.danel.gymex.domain.gym.timetable.Activity;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ActivityJob {

    private final TimetableService timetableService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void changeActivityStatus() {
        List<Activity> activitiesToStart = timetableService.activitiesForStatus(ActivityStatus.CREATED);

        activitiesToStart.forEach(activity -> {
            if (activity.shouldStart()) {
                timetableService.startActivity(activity.getId());
            }
        });

        List<Activity> activitiesToFinish = timetableService.activitiesForStatus(ActivityStatus.IN_PROGRESS);

        activitiesToFinish.forEach(activity -> {
            if (activity.shouldFinish()) {
                timetableService.finishActivity(activity.getId());
            }
        });
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void changeActivityAttendancesStatus() {
        List<Activity> activities = timetableService.activitiesForStatus(ActivityStatus.FINISHED);

        activities.forEach(activity -> timetableService.markAllAsAttendedFalse(activity.getId()));
    }
}
