package pl.danel.gymex.domain.person.trainer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.gym.timetable.Activity;
import pl.danel.gymex.domain.person.Person;
import pl.danel.gymex.domain.person.user.User;
import pl.danel.gymex.domain.person.user.command.CreatePerson;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("TRAINER")
public class Trainer extends Person {

    private String description;

    @OneToMany(
            mappedBy = "trainer",
            cascade = CascadeType.ALL
    )
    private List<Activity> activities;

    public Trainer(User user, CreatePerson person) {
        super(user, person);
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.setTrainer(this);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
        activity.setTrainer(null);
    }
}
