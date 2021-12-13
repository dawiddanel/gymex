package pl.danel.gymex.domain.gym;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.gym.command.CreateGym;
import pl.danel.gymex.domain.gym.command.UpdateGym;
import pl.danel.gymex.fixtures.Fixtures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GymTest {

    @Test
    public void testCreateGym() {
        //given
        CreateGym command = Fixtures.createGym()
                .build();

        //when
        Gym gym = Gym.create(command);

        //then
        assertEquals(gym.getName().getValue(), command.getName());
        assertEquals(gym.getCapacity(), command.getCapacity());
        assertEquals(gym.getCapacity(), command.getCapacity());
        assertNotNull(gym.getAssortment());
        assertEquals(gym.getTimetables().size(), 1);
        assertEquals(gym.getTimetables().get(0).getStartDate(), command.getCreateTimetable().getStartDate());
        assertEquals(gym.getTimetables().get(0).getEndDate(), command.getCreateTimetable().getEndDate());
        assertEquals(gym.getAddress().getCountry().getValue(), command.getAddress().getCountry());
    }

    @Test
    public void testUpdateGym() {
        //given
        CreateGym command = Fixtures.createGym()
                .build();
        UpdateGym command2 = Fixtures.updateGym()
                .build();

        Gym gym = Gym.create(command);
        //when
        gym.update(command2);

        //then
        assertEquals(gym.getName().getValue(), command2.getName());
        assertEquals(gym.getCapacity(), command2.getCapacity());
        assertEquals(gym.getCapacity(), command2.getCapacity());
        assertNotNull(gym.getAssortment());
        assertEquals(gym.getTimetables().size(), 1);
        assertEquals(gym.getTimetables().get(0).getStartDate(), command.getCreateTimetable().getStartDate());
        assertEquals(gym.getTimetables().get(0).getEndDate(), command.getCreateTimetable().getEndDate());
        assertEquals(gym.getAddress().getCountry().getValue(), command2.getAddress().getCountry());
    }

    @Test
    public void testAddEmptyTimetable() {
        //given
        CreateGym command = Fixtures.createGym()
                .build();

        Gym gym = Gym.create(command);

        //when
        gym.addEmptyTimetable();

        //then
        assertEquals(gym.getTimetables().size(), 2);
        assertEquals(gym.getTimetables().get(1).getStartDate(), command.getCreateTimetable().getEndDate().plusDays(1));
        assertEquals(gym.getTimetables().get(1).getEndDate(), command.getCreateTimetable().getEndDate().plusDays(15));
    }

}
