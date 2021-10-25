package pl.danel.gymex.domain.person;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("OWNER")
public class Owner extends Person {
}
