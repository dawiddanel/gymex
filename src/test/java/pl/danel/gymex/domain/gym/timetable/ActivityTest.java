package pl.danel.gymex.domain.gym.timetable;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.common.ActivityStatus;
import pl.danel.gymex.domain.gym.timetable.activities.Attendance;
import pl.danel.gymex.domain.gym.timetable.command.*;
import pl.danel.gymex.domain.person.Person;
import pl.danel.gymex.domain.person.member.Member;
import pl.danel.gymex.domain.person.user.User;
import pl.danel.gymex.domain.person.user.command.CreatePerson;
import pl.danel.gymex.fixtures.Fixtures;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    @Test
    public void testCreateActivity() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .build();

        //when
        Activity activity = Activity.create(command);

        //then
        assertEquals(activity.getDefinition(), command.getActivityDefinition());
        assertEquals(activity.getTrainer(), command.getTrainer());
        assertEquals(activity.getStartTime(), command.getStartTime());
        assertEquals(activity.getEndTime(), command.getEndTime());
        assertEquals(activity.getCapacity(), command.getCapacity());
        assertEquals(activity.getStatus(), ActivityStatus.CREATED);
    }

    @Test
    public void testCreateActivityNullCommand() {
        //given
        CreateActivity command = null;
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Activity.create(command);
        });

        //then
        assertEquals("command cannot be null", exception.getMessage());
    }

    @Test
    public void testCreateActivityNullDefinition() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .activityDefinition(null)
                .build();
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Activity.create(command);
        });

        //then
        assertEquals("definition cannot be null", exception.getMessage());
    }

    @Test
    public void testCreateActivityNullTrainer() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .trainer(null)
                .build();
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Activity.create(command);
        });

        //then
        assertEquals("trainer cannot be null", exception.getMessage());
    }

    @Test
    public void testUpdateActivity() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .build();
        UpdateActivity command2 = Fixtures.updateActivity()
                .build();

        Activity activity = Activity.create(command);

        //when
        activity.update(command2);

        //then
        assertEquals(activity.getDefinition(), command2.getActivityDefinition());
        assertEquals(activity.getTrainer(), command2.getTrainer());
        assertEquals(activity.getStartTime(), command2.getStartTime());
        assertEquals(activity.getEndTime(), command2.getEndTime());
        assertEquals(activity.getCapacity(), command2.getCapacity());
        assertEquals(activity.getStatus(), ActivityStatus.CREATED);
    }

    @Test
    public void testUpdateActivityNullCommand() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .build();
        UpdateActivity command2 = null;

        Activity activity = Activity.create(command);

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            activity.update(command2);
        });

        //then
        assertEquals("command cannot be null", exception.getMessage());
    }

    @Test
    public void testUpdateActivityNullDefinition() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .build();
        UpdateActivity command2 = Fixtures.updateActivity()
                .activityDefinition(null)
                .build();

        Activity activity = Activity.create(command);

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            activity.update(command2);
        });

        //then
        assertEquals("definition cannot be null", exception.getMessage());
    }

    @Test
    public void testUpdateActivityNullTrainer() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .build();
        UpdateActivity command2 = Fixtures.updateActivity()
                .trainer(null)
                .build();

        Activity activity = Activity.create(command);

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            activity.update(command2);
        });

        //then
        assertEquals("trainer cannot be null", exception.getMessage());
    }

    @Test
    public void testAddParticipant() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .capacity(10)
                .build();
        CreatePerson createPerson = Fixtures.createPerson()
                .build();
        User user = new User();

        Activity activity = Activity.create(command);
        Member member = Person.createMember(user, createPerson);

        AddParticipant addParticipant = Fixtures.addParticipant()
                .member(member)
                .build();

        //when
        activity.addParticipant(addParticipant);

        //then
        assertEquals(activity.getParticipants().size(), 1);
        assertEquals(activity.getParticipants().get(0), member);
        assertEquals(member.getAttendances().size(), 1);
    }

    @Test
    public void testRemoveParticipant() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .capacity(10)
                .build();
        CreatePerson createPerson = Fixtures.createPerson()
                .build();
        User user = new User();

        Activity activity = Activity.create(command);
        Member member = Person.createMember(user, createPerson);

        AddParticipant addParticipant = Fixtures.addParticipant()
                .member(member)
                .build();
        activity.addParticipant(addParticipant);

        RemoveParticipant removeParticipant = Fixtures.removeParticipant()
                .member(member)
                .build();
        //when
        activity.removeParticipant(removeParticipant);

        //then
        assertEquals(activity.getParticipants().size(), 0);
        assertEquals(member.getAttendances().size(), 0);
    }

    @Test
    public void testConfirmAttendanceParticipant() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .capacity(10)
                .build();
        CreatePerson createPerson = Fixtures.createPerson()
                .build();
        User user = new User();

        Activity activity = Activity.create(command);
        Member member = Person.createMember(user, createPerson);

        AddParticipant addParticipant = Fixtures.addParticipant()
                .member(member)
                .build();
        activity.addParticipant(addParticipant);

        ConfirmAttendance confirmAttendance = Fixtures.confirmAttendance()
                .member(member)
                .build();
        //when
        activity.confirmAttendance(confirmAttendance);

        //then
        Attendance attendance = activity.attendanceByMember(member);
        assertTrue(attendance.getAttended());
    }

    @Test
    public void testResignAttendanceParticipant() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .capacity(10)
                .build();
        CreatePerson createPerson = Fixtures.createPerson()
                .build();
        User user = new User();

        Activity activity = Activity.create(command);
        Member member = Person.createMember(user, createPerson);

        AddParticipant addParticipant = Fixtures.addParticipant()
                .member(member)
                .build();
        activity.addParticipant(addParticipant);

        ResignAttendance resignAttendance = Fixtures.resignAttendance()
                .member(member)
                .build();
        //when
        activity.resignAttendance(resignAttendance);

        //then
        Attendance attendance = activity.attendanceByMember(member);
        assertFalse(attendance.getAttended());
    }

    @Test
    public void testShouldActivityStart() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .startTime(LocalDateTime.now().minusMinutes(5))
                .build();

        //when
        Activity activity = Activity.create(command);

        //then
        assertTrue(activity.shouldStart());
    }

    @Test
    public void testShouldActivityFinish() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .endTime(LocalDateTime.now().minusMinutes(5))
                .build();

        //when
        Activity activity = Activity.create(command);

        //then
        assertTrue(activity.shouldFinish());
    }

    @Test
    public void testActivityStart() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .build();

        Activity activity = Activity.create(command);

        //when
        activity.start();

        //then
        assertEquals(activity.getStatus(), ActivityStatus.IN_PROGRESS);
    }

    @Test
    public void testActivityCancel() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .build();

        Activity activity = Activity.create(command);

        //when
        activity.cancel();

        //then
        assertEquals(activity.getStatus(), ActivityStatus.CANCELLED);
    }

    @Test
    public void testActivityFinish() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .build();

        Activity activity = Activity.create(command);

        //when
        activity.finish();

        //then
        assertEquals(activity.getStatus(), ActivityStatus.FINISHED);
    }


    @Test
    public void testMatkAllAttendanceFalse() {
        //given
        CreateActivity command = Fixtures.createActivity()
                .capacity(10)
                .build();
        CreatePerson createPerson = Fixtures.createPerson()
                .build();
        User user = new User();

        Activity activity = Activity.create(command);
        Member member = Person.createMember(user, createPerson);

        AddParticipant addParticipant = Fixtures.addParticipant()
                .member(member)
                .build();

        activity.addParticipant(addParticipant);

        //when
        activity.markAllAttendanceFalse();

        //then
        assertEquals(activity.getParticipants().size(), 1);
        assertEquals(activity.getParticipants().get(0), member);
        assertEquals(member.getAttendances().size(), 1);
        assertFalse(member.getAttendances().get(0).getAttended());
    }


}
