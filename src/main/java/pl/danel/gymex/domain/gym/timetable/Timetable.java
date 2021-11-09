package pl.danel.gymex.domain.gym.timetable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import pl.danel.gymex.domain.asserts.DomainAsserts;
import pl.danel.gymex.domain.asserts.NotFoundException;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.gym.command.CreateTimetable;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TIMETABLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timetable_sequence")
    @SequenceGenerator(name = "timetable_sequence", sequenceName = "SEQ_TIMETABLE", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Gym gym;

    @OneToMany(
            mappedBy = "timetable",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Activity> activities;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private Timetable(Gym gym, LocalDate startDate) {
        this.gym = gym;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(14);
        this.active = true;
    }

    public static Timetable emptyTimetable(Gym gym, LocalDate startDate) {
        return new Timetable(gym, startDate);
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.setTimetable(this);
        activity.getTrainer().addActivity(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
        activity.setTimetable(null);
        activity.getTrainer().removeActivity(activity);
    }

    public Activity activityById(Long id) {
        DomainAsserts.assertState(id != null, "ACTIVITY id cannot be null");
        return activities.stream()
                .filter(activity -> activity.getId().equals(id))
                .findAny().orElseThrow(() -> new NotFoundException("no such ACTIVITY present in TIMETABLE"));
    }

    public boolean actual() {
        LocalDate now = LocalDate.now();
        return (now.isAfter(this.startDate) || now.isEqual(now))
                && (this.endDate.isAfter(now) || this.endDate.isEqual(now));
    }

    public void markInactive() {
        this.active = false;
    }

}
