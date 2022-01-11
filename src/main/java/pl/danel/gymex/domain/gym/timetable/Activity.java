package pl.danel.gymex.domain.gym.timetable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import pl.danel.gymex.domain.asserts.DomainAsserts;
import pl.danel.gymex.domain.asserts.NotFoundException;
import pl.danel.gymex.domain.common.ActivityStatus;
import pl.danel.gymex.domain.gym.timetable.activities.Attendance;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.command.*;
import pl.danel.gymex.domain.person.member.Member;
import pl.danel.gymex.domain.person.trainer.Trainer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ACTIVITY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activities_sequence")
    @SequenceGenerator(name = "activities_sequence", sequenceName = "SEQ_ACTIVITIES", allocationSize = 1, initialValue = 100)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Timetable timetable;

    @ManyToOne(fetch = FetchType.LAZY)
    private ActivityDefinition definition;

    @ManyToOne(fetch = FetchType.LAZY)
    private Trainer trainer;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "activity_member",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> participants = new ArrayList<>();

    @OneToMany(
            mappedBy = "activity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Attendance> attendance = new ArrayList<>();

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private ActivityStatus status;

    private Activity(CreateActivity command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        DomainAsserts.assertArgumentNotNull(command.getActivityDefinition(), "definition cannot be null");
        DomainAsserts.assertArgumentNotNull(command.getTrainer(), "trainer cannot be null");
        this.definition = command.getActivityDefinition();
        this.trainer = command.getTrainer();
        this.startTime = command.getStartTime();
        this.endTime = command.getEndTime();
        this.capacity = command.getCapacity();
        this.status = ActivityStatus.CREATED;
    }

    public static Activity create(CreateActivity command) {
        return new Activity(command);
    }

    public void update(UpdateActivity command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        DomainAsserts.assertArgumentNotNull(command.getActivityDefinition(), "definition cannot be null");
        DomainAsserts.assertArgumentNotNull(command.getTrainer(), "trainer cannot be null");
        this.definition = command.getActivityDefinition();
        this.trainer = command.getTrainer();
        this.startTime = command.getStartTime();
        this.endTime = command.getEndTime();
        this.capacity = command.getCapacity();
    }

    public void addParticipant(AddParticipant command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        DomainAsserts.assertArgumentNotNull(command.getMember(), "member cannot be null");
        DomainAsserts.assertState(capacity >= participants.size() + 1, "to many PARTICIPANTS");
        DomainAsserts.assertState(startTime.isAfter(LocalDateTime.now()), "activity already happened");

        Attendance attendance = Attendance.create(command.getMember(), this);
        this.participants.add(command.getMember());
        this.attendance.add(attendance);
        command.getMember().addAttendance(attendance);
    }

    public void removeParticipant(RemoveParticipant command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        DomainAsserts.assertArgumentNotNull(command.getMember(), "member cannot be null");
        DomainAsserts.assertState(startTime.isAfter(LocalDateTime.now()), "activity already happened, cannot change parameters");

        Attendance attendance = attendanceByMember(command.getMember());
        this.participants.remove(command.getMember());
        this.attendance.remove(attendance);
        attendance.setActivity(null);
        command.getMember().removeAttendance(attendance);
    }

    public void confirmAttendance(ConfirmAttendance command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        DomainAsserts.assertArgumentNotNull(command.getMember(), "member cannot be null");

        Attendance attendance = attendanceByMember(command.getMember());
        attendance.confirmAttendance();
    }

    public void resignAttendance(ResignAttendance command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        DomainAsserts.assertArgumentNotNull(command.getMember(), "member cannot be null");

        Attendance attendance = attendanceByMember(command.getMember());
        attendance.resignAttendance();
    }

    public Attendance attendanceById(Long id) {
        DomainAsserts.assertArgumentNotNull(id, "ATTENDANCE id cannot be null");
        return attendance.stream()
                .filter(attendance -> attendance.getId().equals(id))
                .findAny().orElseThrow(() -> new NotFoundException("no such ATTENDANCE present in ACTIVITY"));
    }

    public Attendance attendanceByMember(Member member) {
        DomainAsserts.assertArgumentNotNull(member, "MEMBER cannot be null");
        return attendance.stream()
                .filter(attendance -> attendance.getMember().equals(member))
                .findAny().orElseThrow(() -> new NotFoundException("no such ATTENDANCE present in ACTIVITY"));
    }

    public boolean shouldStart() {
        return this.startTime.isBefore(LocalDateTime.now()) || this.startTime.equals(LocalDateTime.now());
    }

    public boolean shouldFinish() {
        return this.endTime.isBefore(LocalDateTime.now())|| this.endTime.equals(LocalDateTime.now());
    }

    public void start() {
        this.status = ActivityStatus.IN_PROGRESS;
    }

    public void markAllAttendanceFalse() {
        this.attendance.stream()
                .filter(attendance1 -> attendance1.getAttended() == null)
                .forEach(Attendance::resignAttendance);
    }

    public void cancel() {
        this.status = ActivityStatus.CANCELLED;
    }

    public void finish() {
        this.status = ActivityStatus.FINISHED;
    }

}
