package pl.danel.gymex.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.danel.gymex.application.gym.timetable.TimetableService;
import pl.danel.gymex.domain.common.ActivityStatus;
import pl.danel.gymex.domain.gym.timetable.Activity;
import pl.danel.gymex.domain.gym.timetable.ActivityRepository;
import pl.danel.gymex.fixtures.Fixtures;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TimetableServiceTest {

    @Autowired
    private TimetableService timetableService;

    @MockBean
    private ActivityRepository activityRepository;

    @Test
    public void testStartActivity() {
        //given
        Activity activity1 = Activity.create(Fixtures.createActivity().build());
        activity1.setId(1L);
        when(activityRepository.getById(any())).thenReturn(activity1);
        //when
        timetableService.startActivity(1L);
        //then
        verify(activityRepository).save(activity1);
    }
}
