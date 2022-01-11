package pl.danel.gymex.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.danel.gymex.application.gym.timetable.TimetableService;
import pl.danel.gymex.domain.common.ActivityStatus;
import pl.danel.gymex.domain.gym.timetable.Activity;
import pl.danel.gymex.fixtures.Fixtures;
import pl.danel.gymex.infrastructure.jobs.ActivityJob;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ActivityJobTest {

    @MockBean
    private TimetableService timetableService;

    @Autowired
    private ActivityJob activityJob;

    @Test
    public void testStartActivityIfShouldStart() {
        //given
        Activity activity1 = Activity.create(Fixtures.createActivity().build());
        activity1.setId(1L);
        activity1.setStatus(ActivityStatus.CREATED);
        activity1.setStartTime(LocalDateTime.now().minusMinutes(10));
        activity1.setEndTime(LocalDateTime.now().plusHours(1));

        Activity activity2 = Activity.create(Fixtures.createActivity().build());
        activity2.setId(2L);
        activity2.setStatus(ActivityStatus.CREATED);
        activity2.setStartTime(LocalDateTime.now().plusMinutes(10));
        activity2.setEndTime(LocalDateTime.now().plusHours(1));

        when(timetableService.activitiesForStatus(any())).thenReturn(List.of(
                activity1, activity2
        ));

        //when
        activityJob.changeActivityStatus();

        //then
        verify(timetableService).startActivity(1L);
    }

}
