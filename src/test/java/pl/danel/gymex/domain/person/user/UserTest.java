package pl.danel.gymex.domain.person.user;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.person.Employee;
import pl.danel.gymex.domain.person.Owner;
import pl.danel.gymex.domain.person.member.Member;
import pl.danel.gymex.domain.person.trainer.Trainer;
import pl.danel.gymex.domain.person.user.command.CreateTechnicalUser;
import pl.danel.gymex.domain.person.user.command.CreateUser;
import pl.danel.gymex.fixtures.Fixtures;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testCreateMemberUser() {
        //given
        CreateUser command = Fixtures.createUser()
                .build();

        //when
        User user = User.createMember(command);

        //then
        assertEquals(user.getUsername().getValue(), command.getUsername());
        assertEquals(user.getEmail().getValue(), command.getEmail());
        assertEquals(user.getRole(), Role.MEMBER);
        assertTrue(user.isActive());
    }

    @Test
    public void testCreateTechnicalUserRoleMember() {
        //given
        CreateTechnicalUser command = Fixtures.createTechnicalUser()
                .role(Role.MEMBER)
                .build();

        //when
        User user = User.createTechnical(command);

        //then
        assertEquals(user.getUsername().getValue(), command.getUsername());
        assertEquals(user.getEmail().getValue(), command.getEmail());
        assertEquals(user.getRole(), Role.MEMBER);
        assertTrue(user.getPerson() instanceof Member);
        assertTrue(user.isActive());
    }

    @Test
    public void testCreateTechnicalUserRoleTrainer() {
        //given
        CreateTechnicalUser command = Fixtures.createTechnicalUser()
                .role(Role.TRAINER)
                .build();

        //when
        User user = User.createTechnical(command);

        //then
        assertEquals(user.getUsername().getValue(), command.getUsername());
        assertEquals(user.getEmail().getValue(), command.getEmail());
        assertEquals(user.getRole(), Role.TRAINER);
        assertTrue(user.getPerson() instanceof Trainer);
        assertTrue(user.isActive());
    }

    @Test
    public void testCreateTechnicalUserRoleOwner() {
        //given
        CreateTechnicalUser command = Fixtures.createTechnicalUser()
                .role(Role.OWNER)
                .build();

        //when
        User user = User.createTechnical(command);

        //then
        assertEquals(user.getUsername().getValue(), command.getUsername());
        assertEquals(user.getEmail().getValue(), command.getEmail());
        assertEquals(user.getRole(), Role.OWNER);
        assertTrue(user.getPerson() instanceof Owner);
        assertTrue(user.isActive());
    }

    @Test
    public void testCreateTechnicalUserRoleEmployee() {
        //given
        CreateTechnicalUser command = Fixtures.createTechnicalUser()
                .role(Role.EMPLOYEE)
                .build();

        //when
        User user = User.createTechnical(command);

        //then
        assertEquals(user.getUsername().getValue(), command.getUsername());
        assertEquals(user.getEmail().getValue(), command.getEmail());
        assertEquals(user.getRole(), Role.EMPLOYEE);
        assertTrue(user.getPerson() instanceof Employee);
        assertTrue(user.isActive());
    }

    @Test
    public void testCreateTechnicalUserRoleMalmorfedEmail() {
        //given
        CreateTechnicalUser command = Fixtures.createTechnicalUser()
                .email("123.sdd")
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            User.createTechnical(command);
        });

        //then
        assertEquals("Email is malmorfed", exception.getMessage());
    }

    @Test
    public void testCreateTechnicalUserNullUsername() {
        //given
        CreateTechnicalUser command = Fixtures.createTechnicalUser()
                .username(null)
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            User.createTechnical(command);
        });

        //then
        assertEquals("Username is null", exception.getMessage());
    }

}
