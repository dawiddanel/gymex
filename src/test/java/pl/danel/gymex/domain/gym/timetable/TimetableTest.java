package pl.danel.gymex.domain.gym.timetable;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.common.ActivityStatus;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.gym.timetable.command.CreateActivity;
import pl.danel.gymex.fixtures.Fixtures;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimetableTest {

    @Test
    public void testCreateTimetable() {
        //given
        Gym gym = new Gym();
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(25);

        //when
        Timetable timetable = Timetable.emptyTimetable(gym, startDate, endDate);

        //then
        assertEquals(timetable.getGym(), gym);
        assertEquals(timetable.getStartDate(), startDate);
        assertEquals(timetable.getEndDate(), endDate);
        assertEquals(timetable.getOrderNumber(), 1);
    }

    @Test
    public void testCreateNextTimetable() {
        //given
        Gym gym = new Gym();
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(25);

        Timetable timetable = Timetable.emptyTimetable(gym, startDate, endDate);
        //when
        Timetable timetable2 = Timetable.emptyTimetable(gym, timetable.getEndDate().plusDays(1), timetable.getOrderNumber() + 1);

        //then
        assertEquals(timetable2.getGym(), gym);
        assertEquals(timetable2.getStartDate(), timetable.getEndDate().plusDays(1));
        assertEquals(timetable2.getEndDate(), timetable.getEndDate().plusDays(15));
        assertEquals(timetable2.getOrderNumber(), 2);
    }

    @Test
    public void testAddActivity() {
        //given
        Gym gym = new Gym();
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(25);

        Timetable timetable = Timetable.emptyTimetable(gym, startDate, endDate);
        CreateActivity createActivity = Fixtures.createActivity()
                .build();
        Activity activity = Activity.create(createActivity);
        //when

        timetable.addActivity(activity);

        //then
        assertEquals(timetable.getGym(), gym);
        assertEquals(timetable.getActivities().size(), 1);
        assertEquals(timetable.getActivities().get(0), activity);
        assertEquals(timetable.getActivities().get(0).getStatus(), ActivityStatus.CREATED);
    }

    @Test
    public void testCancelActivity() {
        //given
        Gym gym = new Gym();
        LocalDate startDate = LocalDate.now().plusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(25);

        Timetable timetable = Timetable.emptyTimetable(gym, startDate, endDate);
        CreateActivity createActivity = Fixtures.createActivity()
                .build();
        Activity activity = Activity.create(createActivity);
        timetable.addActivity(activity);

        //when
        timetable.cancelActivity(activity);

        //then
        assertEquals(timetable.getGym(), gym);
        assertEquals(timetable.getActivities().size(), 1);
        assertEquals(timetable.getActivities().get(0), activity);
        assertEquals(timetable.getActivities().get(0).getStatus(), ActivityStatus.CANCELLED);
    }

    @Test
    public void testActual() {
        //given
        Gym gym = new Gym();
        LocalDate startDate = LocalDate.now().minusDays(10);
        LocalDate endDate = LocalDate.now().plusDays(25);


        //when
        Timetable timetable = Timetable.emptyTimetable(gym, startDate, endDate);


        //then
        assertTrue(timetable.actual());
    }


}
