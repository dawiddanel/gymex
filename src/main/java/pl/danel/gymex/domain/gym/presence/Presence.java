package pl.danel.gymex.domain.gym.presence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.danel.gymex.domain.asserts.DomainAsserts;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.person.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRESENCE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presence_sequence")
    @SequenceGenerator(name = "presence_sequence", sequenceName = "SEQ_PRESENCE", allocationSize = 1, initialValue = 100)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Gym gym;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Presence(Gym gym, Member member) {
        DomainAsserts.assertState(gym != null, "gym cannot be null");
        DomainAsserts.assertState(member != null, "member cannot be null");
        this.gym = gym;
        this.member = member;
        this.startTime = LocalDateTime.now();
    }

    public static Presence create(Gym gym, Member member) {
        return new Presence(gym, member);
    }

    public void finish() {
        DomainAsserts.assertState(endTime == null, "Presence already ended!");
        this.endTime = LocalDateTime.now();
    }

    public boolean finished() {
        return this.endTime != null;
    }

}
