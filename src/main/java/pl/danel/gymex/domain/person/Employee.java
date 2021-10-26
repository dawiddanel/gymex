package pl.danel.gymex.domain.person;

import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.person.user.User;
import pl.danel.gymex.domain.person.user.command.CreatePerson;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("EMPLOYEE")
public class Employee extends Person {
    public Employee(User user, CreatePerson person) {
        super(user, person);
    }
}
