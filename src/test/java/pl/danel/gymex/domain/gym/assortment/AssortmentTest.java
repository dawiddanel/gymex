package pl.danel.gymex.domain.gym.assortment;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipment;
import pl.danel.gymex.fixtures.Fixtures;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssortmentTest {

    @Test
    public void testCreateAssortment() {
        //given
        Gym gym = new Gym();

        //when
        Assortment assortment = Assortment.emptyAssortment(gym);

        //then
        assertEquals(assortment.getGym(), gym);
    }

    @Test
    public void testAddEquipment() {
        //given
        Gym gym = new Gym();
        Assortment assortment = Assortment.emptyAssortment(gym);
        CreateEquipment createEquipment = Fixtures.createEquipment()
                .build();
        Equipment equipment = Equipment.create(createEquipment);
        //when

        assortment.addEquipment(equipment);

        //then
        assertEquals(assortment.getGym(), gym);
        assertEquals(assortment.getEquipments().size(), 1);
        assertEquals(assortment.getEquipments().get(0), equipment);
    }

    @Test
    public void testRemoveEquipment() {
        //given
        Gym gym = new Gym();
        Assortment assortment = Assortment.emptyAssortment(gym);
        CreateEquipment createEquipment = Fixtures.createEquipment()
                .build();
        Equipment equipment = Equipment.create(createEquipment);
        assortment.addEquipment(equipment);

        //when
        assortment.removeEquipment(equipment);

        //then
        assertEquals(assortment.getGym(), gym);
        assertEquals(assortment.getEquipments().size(), 0);
    }

}
