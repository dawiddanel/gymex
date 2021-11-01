package pl.danel.gymex.domain.gym.timetable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.danel.gymex.domain.gym.assortment.Equipment;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipment;
import pl.danel.gymex.domain.gym.timetable.Timetable;
import pl.danel.gymex.domain.gym.timetable.activities.Attendance;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.command.CreateActivity;
import pl.danel.gymex.domain.gym.timetable.command.UpdateActivity;
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
@Setter(AccessLevel.PROTECTED)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activities_sequence")
    @SequenceGenerator(name = "activities_sequence", sequenceName = "SEQ_ACTIVITIES", allocationSize = 1)
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

    private Integer duration;

    private Integer capacity;

    private Activity(CreateActivity command) {
        this.definition = command.getActivityDefinition();
        this.trainer = command.getTrainer();
        this.startTime = command.getStartTime();
        this.duration = command.getDuration();
        this.capacity = command.getCapacity();
    }

    public static Activity create(CreateActivity command) {
        return new Activity(command);
    }

    public void update(UpdateActivity command) {
        this.definition = command.getActivityDefinition();
        this.trainer = command.getTrainer();
        this.startTime = command.getStartTime();
        this.duration = command.getDuration();
        this.capacity = command.getCapacity();
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

}
