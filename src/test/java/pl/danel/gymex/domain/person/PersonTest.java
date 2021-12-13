package pl.danel.gymex.domain.person;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.person.member.Member;
import pl.danel.gymex.domain.person.trainer.Trainer;
import pl.danel.gymex.domain.person.user.User;
import pl.danel.gymex.domain.person.user.command.CreatePerson;
import pl.danel.gymex.fixtures.Fixtures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonTest {

    @Test
    public void testCreateMember() {
        //given
        CreatePerson command = Fixtures.createPerson()
                .build();

        User user = new User();
        //when
        Member member = Person.createMember(user, command);

        //then
        assertEquals(member.getFirstName().getValue(), command.getFirstName());
        assertEquals(member.getLastName().getValue(), command.getLastName());
        assertEquals(member.getPesel().getValue(), command.getPesel());
        assertEquals(member.getBirthDate(), command.getBirthDate());
        assertEquals(member.getUser(), user);
    }

    @Test
    public void testCreateTrainer() {
        //given
        CreatePerson command = Fixtures.createPerson()
                .build();

        User user = new User();
        //when
        Trainer trainer = Person.createTrainer(user, command);

        //then
        assertEquals(trainer.getFirstName().getValue(), command.getFirstName());
        assertEquals(trainer.getLastName().getValue(), command.getLastName());
        assertEquals(trainer.getPesel().getValue(), command.getPesel());
        assertEquals(trainer.getBirthDate(), command.getBirthDate());
        assertEquals(trainer.getUser(), user);
    }

    @Test
    public void testCreateEmployee() {
        //given
        CreatePerson command = Fixtures.createPerson()
                .build();

        User user = new User();
        //when
        Employee employee = Person.createEmployee(user, command);

        //then
        assertEquals(employee.getFirstName().getValue(), command.getFirstName());
        assertEquals(employee.getLastName().getValue(), command.getLastName());
        assertEquals(employee.getPesel().getValue(), command.getPesel());
        assertEquals(employee.getBirthDate(), command.getBirthDate());
        assertEquals(employee.getUser(), user);
    }

    @Test
    public void testCreateOwner() {
        //given
        CreatePerson command = Fixtures.createPerson()
                .build();

        User user = new User();
        //when
        Owner owner = Person.createOwner(user, command);

        //then
        assertEquals(owner.getFirstName().getValue(), command.getFirstName());
        assertEquals(owner.getLastName().getValue(), command.getLastName());
        assertEquals(owner.getPesel().getValue(), command.getPesel());
        assertEquals(owner.getBirthDate(), command.getBirthDate());
        assertEquals(owner.getUser(), user);
    }

    @Test
    public void testCreateMemberNullUser() {
        //given
        CreatePerson command = Fixtures.createPerson()
                .build();

        User user = null;
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Person.createMember(user, command);
        });

        //then
        assertEquals("user cannot be null", exception.getMessage());
    }

    @Test
    public void testCreateMemberNullCommand() {
        //given
        CreatePerson command = null;

        User user = new User();
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Person.createMember(user, command);
        });

        //then
        assertEquals("command cannot be null", exception.getMessage());
    }

    @Test
    public void testCreateMemberNullFirstname() {
        //given
        CreatePerson command = Fixtures.createPerson()
                .firstName(null)
                .build();

        User user = new User();
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Person.createMember(user, command);
        });

        //then
        assertEquals("Firstname is null", exception.getMessage());
    }

    @Test
    public void testCreateMemberNullLastname() {
        //given
        CreatePerson command = Fixtures.createPerson()
                .lastName(null)
                .build();

        User user = new User();
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Person.createMember(user, command);
        });

        //then
        assertEquals("Lastname is null", exception.getMessage());
    }

    @Test
    public void testCreateMemberMalformedPesel() {
        //given
        CreatePerson command = Fixtures.createPerson()
                .pesel("9831")
                .build();

        User user = new User();
        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Person.createMember(user, command);
        });

        //then
        assertEquals("Pesel is malmorfed", exception.getMessage());
    }


}
