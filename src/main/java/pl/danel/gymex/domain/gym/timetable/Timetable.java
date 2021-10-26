package pl.danel.gymex.domain.gym.timetable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.gym.timetable.activities.Activity;

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
}
