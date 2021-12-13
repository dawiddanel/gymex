package pl.danel.gymex.domain.gym.pass;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.gym.command.CreatePass;
import pl.danel.gymex.domain.person.member.Member;
import pl.danel.gymex.fixtures.Fixtures;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PassTest {

    @Test
    public void testCreatePass() {
        //given
        CreatePass command = Fixtures.createPass()
                .build();

        //when
        Pass pass = Pass.create(command);

        //then
        assertEquals(pass.getStartDate(), command.getStartDate());
        assertEquals(pass.getEndDate(), command.getEndDate());
        assertTrue(pass.isActive());
    }

    @Test
    public void testCreatePassStartDateError() {
        //given
        CreatePass command = Fixtures.createPass()
                .startDate(LocalDate.now().minusDays(1))
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Pass.create(command);
        });

        //then
        assertEquals("start date must be in future or same day", exception.getMessage());
    }

    @Test
    public void testCreatePassEndDateError() {
        //given
        CreatePass command = Fixtures.createPass()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().minusDays(1))
                .build();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Pass.create(command);
        });

        //then
        assertEquals("end date must be after start date", exception.getMessage());
    }

    @Test
    public void testPassMarkInactive() {
        //given
        CreatePass command = Fixtures.createPass()
                .build();
        Pass pass = Pass.create(command);
        pass.markInactive();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, pass::markInactive);

        //then
        assertEquals("pass cannot be inactive", exception.getMessage());
    }

    @Test
    public void testPassMarkInactiveFromInactiveError() {
        //given
        CreatePass command = Fixtures.createPass()
                .build();
        Pass pass = Pass.create(command);

        //when
        pass.markInactive();

        //then
        assertFalse(pass.isActive());
    }

    @Test
    public void testPassSetMember() {
        //given
        CreatePass command = Fixtures.createPass()
                .build();
        Pass pass = Pass.create(command);

        Member member = new Member();

        //when
        pass.setMember(member);

        //then
        assertEquals(pass.getMember(), member);
    }

    @Test
    public void testPassSetMemberTwiceError() {
        //given
        CreatePass command = Fixtures.createPass()
                .build();
        Pass pass = Pass.create(command);

        Member member = new Member();
        pass.setMember(member);

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            pass.setMember(member);
        });

        //then
        assertEquals("member must be null", exception.getMessage());

    }

}
