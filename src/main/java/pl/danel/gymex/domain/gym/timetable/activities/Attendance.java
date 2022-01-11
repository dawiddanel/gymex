package pl.danel.gymex.domain.gym.timetable.activities;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.danel.gymex.domain.asserts.DomainAsserts;
import pl.danel.gymex.domain.gym.timetable.Activity;
import pl.danel.gymex.domain.gym.timetable.command.AddParticipant;
import pl.danel.gymex.domain.person.member.Member;

import javax.persistence.*;

@Entity
@Table(name = "ATTENDANCE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_sequence")
    @SequenceGenerator(name = "attendance_sequence", sequenceName = "SEQ_ATTENDANCE", allocationSize = 1, initialValue = 100)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Boolean attended;

    private Attendance(Member member, Activity activity) {
        DomainAsserts.assertArgumentNotNull(member, "member cannot be null");
        DomainAsserts.assertArgumentNotNull(activity, "activity cannot be null");
        this.activity = activity;
        this.member = member;
        this.attended = null;
    }

    public static Attendance create(Member member, Activity activity) {
        return new Attendance(member, activity);
    }

    public void confirmAttendance() {
        DomainAsserts.assertState(this.attended == null || !this.attended, "wrong attendance parameter");
        this.attended = true;
    }

    public void resignAttendance() {
        DomainAsserts.assertState(this.attended == null || this.attended, "wrong attendance parameter");
        this.attended = false;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
