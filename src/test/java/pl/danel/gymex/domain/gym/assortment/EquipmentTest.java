package pl.danel.gymex.domain.gym.assortment;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipment;
import pl.danel.gymex.fixtures.Fixtures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EquipmentTest {

    @Test
    public void testCreateEquipment() {
        //given
        CreateEquipment command = Fixtures.createEquipment()
                .build();

        //when
        Equipment equipment = Equipment.create(command);

        //then
        assertEquals(equipment.getQuantity().getValue(), command.getQuantity());
        assertEquals(equipment.getDefinition(), command.getDefinition());
    }

    @Test
    public void testCreateEquipmentNullCommand() {
        //given
        CreateEquipment command = null;
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Equipment.create(command);
        });

        //then
        assertEquals("command cannot be null", exception.getMessage());
    }

    @Test
    public void testCreateEquipmentNullDefinition() {
        //given
        CreateEquipment command = Fixtures.createEquipment()
                .definition(null)
                .build();
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Equipment.create(command);
        });

        //then
        assertEquals("definition cannot be null", exception.getMessage());
    }

    @Test
    public void testCreateEquipmentWrongQuantity() {
        //given
        CreateEquipment command = Fixtures.createEquipment()
                .quantity(-2)
                .build();
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Equipment.create(command);
        });

        //then
        assertEquals("quantity min value is 1", exception.getMessage());
    }

}
