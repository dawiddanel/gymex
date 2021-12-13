package pl.danel.gymex.domain.gym.assortment;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipmentDefinition;
import pl.danel.gymex.domain.gym.assortment.command.UpdateEquipmentDefinition;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinition;
import pl.danel.gymex.fixtures.Fixtures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EquipmentDefinitionTest {

    @Test
    public void testCreateEquipmentDefinition() {
        //given
        CreateEquipmentDefinition command = Fixtures.createEquipmentDefinition()
                .build();

        //when
        EquipmentDefinition equipmentDefinition = EquipmentDefinition.create(command);

        //then
        assertEquals(equipmentDefinition.getName().getValue(), command.getName());
        assertEquals(equipmentDefinition.getDescription().getValue(), command.getDescription());
        assertEquals(equipmentDefinition.getType(), command.getType());
        assertEquals(equipmentDefinition.getPurpose(), command.getPurpose());
        assertEquals(equipmentDefinition.getWeight(), command.getWeight());
        assertEquals(equipmentDefinition.getAimedBodyParts(), command.getAimedBodyParts());
    }

    @Test
    public void testCreateEquipmentNullCommand() {
        //given
        CreateEquipmentDefinition command = null;
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            EquipmentDefinition.create(command);
        });

        //then
        assertEquals("command cannot be null", exception.getMessage());
    }

    @Test
    public void testCreateEquipmentNullName() {
        //given
        CreateEquipmentDefinition command = Fixtures.createEquipmentDefinition()
                .name(null)
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            EquipmentDefinition.create(command);
        });

        //then
        assertEquals("Name is null", exception.getMessage());
    }

    @Test
    public void testCreateEquipmentNullDescription() {
        //given
        CreateEquipmentDefinition command = Fixtures.createEquipmentDefinition()
                .description(null)
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            EquipmentDefinition.create(command);
        });

        //then
        assertEquals("Description is null", exception.getMessage());
    }

    @Test
    public void testUpdateEquipmentDefinition() {
        //given
        CreateEquipmentDefinition command = Fixtures.createEquipmentDefinition()
                .build();
        UpdateEquipmentDefinition command2 = Fixtures.createUpdateEquipmentDefinition()
                .build();

        //when
        EquipmentDefinition equipmentDefinition = EquipmentDefinition.create(command);
        equipmentDefinition.update(command2);

        //then
        assertEquals(equipmentDefinition.getName().getValue(), command2.getName());
        assertEquals(equipmentDefinition.getDescription().getValue(), command2.getDescription());
        assertEquals(equipmentDefinition.getType(), command2.getType());
        assertEquals(equipmentDefinition.getPurpose(), command2.getPurpose());
        assertEquals(equipmentDefinition.getWeight(), command2.getWeight());
        assertEquals(equipmentDefinition.getAimedBodyParts(), command2.getAimedBodyParts());
    }

    @Test
    public void testUpdateEquipmentNullCommand() {
        //given
        CreateEquipmentDefinition command = Fixtures.createEquipmentDefinition().build();
        UpdateEquipmentDefinition command2 = null;

        //when
        EquipmentDefinition equipmentDefinition = EquipmentDefinition.create(command);

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            equipmentDefinition.update(command2);
        });

        //then
        assertEquals("command cannot be null", exception.getMessage());
    }

    @Test
    public void testUpdateEquipmentNullName() {
        //given
        CreateEquipmentDefinition command = Fixtures.createEquipmentDefinition()
                .build();
        UpdateEquipmentDefinition command2 = Fixtures.createUpdateEquipmentDefinition()
                .name(null)
                .build();

        EquipmentDefinition equipmentDefinition = EquipmentDefinition.create(command);

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            equipmentDefinition.update(command2);
        });

        //then
        assertEquals("Name is null", exception.getMessage());
    }

    @Test
    public void testUpdateEquipmentNullDescription() {
        //given
        CreateEquipmentDefinition command = Fixtures.createEquipmentDefinition()
                .build();
        UpdateEquipmentDefinition command2 = Fixtures.createUpdateEquipmentDefinition()
                .description(null)
                .build();

        EquipmentDefinition equipmentDefinition = EquipmentDefinition.create(command);

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            equipmentDefinition.update(command2);
        });

        //then
        assertEquals("Description is null", exception.getMessage());
    }
}
