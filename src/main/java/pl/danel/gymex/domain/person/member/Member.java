package pl.danel.gymex.domain.person.member;

import pl.danel.gymex.domain.gym.pass.Pass;
import pl.danel.gymex.domain.gym.timetable.activities.Activity;
import pl.danel.gymex.domain.gym.timetable.activities.Attendance;
import pl.danel.gymex.domain.person.Person;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
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
}
