package pl.danel.gymex.infrastructure.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.danel.gymex.application.person.PersonService;
import pl.danel.gymex.domain.gym.pass.Pass;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class PassJob {

    private final PersonService personService;

    @Scheduled(cron = "0 0 2 * * *")
    public void createNewTimetable() {
        List<Pass> passes = personService.overduePasses();
        passes.forEach(pass -> personService.overduePass(pass.getId()));
    }
}
