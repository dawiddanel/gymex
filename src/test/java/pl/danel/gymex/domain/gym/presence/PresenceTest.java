package pl.danel.gymex.domain.gym.presence;

import org.junit.jupiter.api.Test;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.person.member.Member;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PresenceTest {

    @Test
    public void testCreatePresence() {
        //given
        Gym gym = new Gym();
        Member member = new Member();

        //when
        Presence presence = Presence.create(gym, member);

        //then
        assertEquals(presence.getGym(), gym);
        assertEquals(presence.getMember(), member);
        assertEquals(presence.getStartTime().toLocalDate(), LocalDateTime.now().toLocalDate());
        assertFalse(presence.finished());
    }

    @Test
    public void testCreatePresenceNullGym() {
        //given
        Gym gym = null;
        Member member = new Member();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Presence.create(gym, member);
        });

        //then
        assertEquals("gym cannot be null", exception.getMessage());
    }

    @Test
    public void testCreatePresenceNullMember() {
        //given
        Gym gym = new Gym();
        Member member = null;

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, () -> {
            Presence.create(gym, member);
        });

        //then
        assertEquals("member cannot be null", exception.getMessage());
    }

    @Test
    public void testFinishPresence() {
        //given
        Gym gym = new Gym();
        Member member = new Member();

        Presence presence = Presence.create(gym, member);

        //when
        presence.finish();

        //then
        assertEquals(presence.getEndTime().toLocalDate(), LocalDateTime.now().toLocalDate());
        assertTrue(presence.finished());
    }

    @Test
    public void testFinishPresenceAlreadyFinished() {
        //given
        Gym gym = new Gym();
        Member member = new Member();

        Presence presence = Presence.create(gym, member);
        presence.finish();

        //when
        InvalidStateException exception = assertThrows(InvalidStateException.class, presence::finish);

        //then
        assertEquals("Presence already ended!", exception.getMessage());
    }

}
