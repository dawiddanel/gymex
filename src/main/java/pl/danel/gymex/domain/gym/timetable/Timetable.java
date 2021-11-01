package pl.danel.gymex.domain.gym.timetable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import pl.danel.gymex.domain.asserts.DomainAsserts;
import pl.danel.gymex.domain.asserts.NotFoundException;
import pl.danel.gymex.domain.gym.Gym;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TIMETABLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Timetable {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Gym gym;

    @OneToMany(
            mappedBy = "timetable",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Activity> activities;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private Timetable(Gym gym) {
        this.gym = gym;
    }

    public static Timetable emptyTimetable(Gym gym) {
        return new Timetable(gym);
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
}
