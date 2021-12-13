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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("TRAINER")
public class Trainer extends Person {

    @OneToMany(
            mappedBy = "trainer",
            cascade = CascadeType.ALL
    )
    private List<Activity> activities = new ArrayList<>();

    public Trainer(User user, CreatePerson person) {
        super(user, person);
    }

}
