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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TIMETABLE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timetable_sequence")
    @SequenceGenerator(name = "timetable_sequence", sequenceName = "SEQ_TIMETABLE", allocationSize = 1, initialValue = 100)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Gym gym;

    @OneToMany(
            mappedBy = "timetable",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Activity> activities = new ArrayList<>();

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer orderNumber;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private Timetable(Gym gym, LocalDate startDate, Integer orderNumber) {
        DomainAsserts.assertArgumentNotNull(gym, "gym cannot be null");
        this.gym = gym;
        this.startDate = startDate;
        this.endDate = startDate.plusDays(14);
        this.orderNumber = orderNumber;
    }

    private Timetable(Gym gym, LocalDate startDate, LocalDate endDate) {
        DomainAsserts.assertArgumentNotNull(gym, "gym cannot be null");
        this.gym = gym;
        this.startDate = startDate;
        this.endDate = endDate;
        this.orderNumber = 1;
    }

    public static Timetable emptyTimetable(Gym gym, LocalDate startDate, LocalDate endDate) {
        return new Timetable(gym, startDate, endDate);
    }

    public static Timetable emptyTimetable(Gym gym, LocalDate startDate, Integer order) {
        return new Timetable(gym, startDate, order);
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.setTimetable(this);
    }

    public void cancelActivity(Activity activity) {
        activity.cancel();
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

}
