package pl.danel.gymex.domain.gym.timetable;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.command.CreateActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.command.UpdateActivityDefinition;
import pl.danel.gymex.fixtures.Fixtures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActivityDefinitionTest {

    @Test
    public void testCreateActivityDefinition() {
        //given
        CreateActivityDefinition command = Fixtures.createActivityDefinition()
                .build();

        //when
        ActivityDefinition activityDefinition = ActivityDefinition.create(command);

        //then
        assertEquals(activityDefinition.getName().getValue(), command.getName());
        assertEquals(activityDefinition.getDescription().getValue(), command.getDescription());
        assertEquals(activityDefinition.getLevel(), command.getLevel());
    }

    @Test
    public void testCreateActivityNullCommand() {
        //given
        CreateActivityDefinition command = null;
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            ActivityDefinition.create(command);
        });

        //then
        assertEquals("command cannot be null", exception.getMessage());
    }

    @Test
    public void testCreateActivityNullName() {
        //given
        CreateActivityDefinition command = Fixtures.createActivityDefinition()
                .name(null)
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            ActivityDefinition.create(command);
        });

        //then
        assertEquals("Name is null", exception.getMessage());
    }

    @Test
    public void testCreateActivityNullDescription() {
        //given
        CreateActivityDefinition command = Fixtures.createActivityDefinition()
                .description(null)
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            ActivityDefinition.create(command);
        });

        //then
        assertEquals("Description is null", exception.getMessage());
    }

    @Test
    public void testUpdateActivityDefinition() {
        //given
        CreateActivityDefinition command = Fixtures.createActivityDefinition()
                .build();
        UpdateActivityDefinition command2 = Fixtures.createUpdateActivityDefinition()
                .build();

        //when
        ActivityDefinition activityDefinition = ActivityDefinition.create(command);
        activityDefinition.update(command2);

        //then
        assertEquals(activityDefinition.getName().getValue(), command2.getName());
        assertEquals(activityDefinition.getDescription().getValue(), command2.getDescription());
        assertEquals(activityDefinition.getLevel(), command2.getLevel());
    }

    @Test
    public void testUpdateActivityNullCommand() {
        //given
        CreateActivityDefinition command = Fixtures.createActivityDefinition().build();
        UpdateActivityDefinition command2 = null;

        //when
        ActivityDefinition activityDefinition = ActivityDefinition.create(command);

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            activityDefinition.update(command2);
        });

        //then
        assertEquals("command cannot be null", exception.getMessage());
    }

    @Test
    public void testUpdateActivityNullName() {
        //given
        CreateActivityDefinition command = Fixtures.createActivityDefinition()
                .build();
        UpdateActivityDefinition command2 = Fixtures.createUpdateActivityDefinition()
                .name(null)
                .build();

        ActivityDefinition activityDefinition = ActivityDefinition.create(command);

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            activityDefinition.update(command2);
        });

        //then
        assertEquals("Name is null", exception.getMessage());
    }

    @Test
    public void testUpdateActivityNullDescription() {
        //given
        CreateActivityDefinition command = Fixtures.createActivityDefinition()
                .build();
        UpdateActivityDefinition command2 = Fixtures.createUpdateActivityDefinition()
                .description(null)
                .build();

        ActivityDefinition activityDefinition = ActivityDefinition.create(command);

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            activityDefinition.update(command2);
        });

        //then
        assertEquals("Description is null", exception.getMessage());
    }

}
