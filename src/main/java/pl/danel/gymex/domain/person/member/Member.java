package pl.danel.gymex.domain.person.member;

import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.gym.pass.Pass;
import pl.danel.gymex.domain.gym.timetable.activities.Attendance;
import pl.danel.gymex.domain.person.Person;
import pl.danel.gymex.domain.person.user.User;
import pl.danel.gymex.domain.person.user.command.CreatePerson;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@DiscriminatorValue("MEMBER")
public class Member extends Person {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "PASS_START")),
            @AttributeOverride(name = "activeStartDate", column = @Column(name = "PASS_ACTIVE_START")),
            @AttributeOverride(name = "endDate", column = @Column(name = "PASS_END")),
    })
    private Pass pass;

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Attendance> attendances;

    public Member(User user, CreatePerson person) {
        super(user, person);
    }
}
