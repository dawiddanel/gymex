package pl.danel.gymex.infrastructure.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.danel.gymex.application.gym.GymService;
import pl.danel.gymex.domain.gym.Gym;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TimetableJob {

    private final GymService gymService;

    @Scheduled(cron = "0 0 1 * * *")
    public void createNewTimetable() {
        List<Gym> gyms = gymService.allGyms();

        gyms.forEach(gym -> {
            if (gym.timetableOverdue()) {
                gymService.createNewTimetable(gym);
            }
        });
    }
}
