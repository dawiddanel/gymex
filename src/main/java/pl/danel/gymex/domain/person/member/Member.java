package pl.danel.gymex.domain.person.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.gym.command.CreatePass;
import pl.danel.gymex.domain.gym.pass.Pass;
import pl.danel.gymex.domain.gym.presence.Presence;
import pl.danel.gymex.domain.gym.timetable.activities.Attendance;
import pl.danel.gymex.domain.person.Person;
import pl.danel.gymex.domain.person.user.User;
import pl.danel.gymex.domain.person.user.command.CreatePerson;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("MEMBER")
public class Member extends Person {

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Pass> passes = new ArrayList<>();

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Attendance> attendances = new ArrayList<>();

    @OneToMany(
            mappedBy = "member",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Presence> presences = new ArrayList<>();

    public Member(User user, CreatePerson person) {
        super(user, person);
    }

    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
        attendance.setMember(this);
    }

    public void removeAttendance(Attendance attendance) {
        attendances.remove(attendance);
        attendance.setMember(null);
    }

    public void addPresence(Presence presence) {
        presences.add(presence);
    }

    public Optional<Pass> actualPass() {
        return this.passes.stream().filter(Pass::isActive).findAny();
    }

    public void addPass(CreatePass command) {
        Optional<Pass> actualPass = actualPass();
        actualPass.ifPresent(Pass::markInactive);
        Pass pass = Pass.create(command);
        passes.add(pass);
        pass.setMember(this);
    }
}
