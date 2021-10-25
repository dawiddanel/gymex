package pl.danel.gymex.domain.person.trainer;

import pl.danel.gymex.domain.gym.timetable.activities.Activity;
import pl.danel.gymex.domain.person.Person;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("TRAINER")
public class Trainer extends Person {
    private String description;

    @OneToMany(
            mappedBy = "trainer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Activity> activities;
}
