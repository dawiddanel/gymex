package pl.danel.gymex.domain.gym.pass;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.danel.gymex.domain.gym.command.CreatePass;
import pl.danel.gymex.domain.person.member.Member;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PASS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Pass {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pass_sequence")
    @SequenceGenerator(name = "pass_sequence", sequenceName = "SEQ_PASS", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private boolean active;

    private LocalDate startDate;

    private LocalDate endDate;

    private Pass(CreatePass command) {
        this.startDate = command.getStartDate();
        this.endDate = command.getEndDate();
        this.active = true;
    }

    public static Pass create(CreatePass command) {
        return new Pass(command);
    }

    public void markInactive() {
        this.active = false;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
