package pl.danel.gymex.domain.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.danel.gymex.domain.common.PersonType;
import pl.danel.gymex.domain.person.member.Member;
import pl.danel.gymex.domain.person.trainer.Trainer;
import pl.danel.gymex.domain.person.user.User;
import pl.danel.gymex.domain.person.user.command.CreatePerson;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type",
        discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Getter
public class Person {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false, name = "person_type")
    private PersonType personType;

    private String firstName;
    private String lastName;
    private String pesel;
    private LocalDate birthDate;

    public Person(User user, CreatePerson person) {
        this.user = user;
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.pesel = person.getPesel();
        this.birthDate = person.getBirthDate();
    }

    public static Person createMember(User user, CreatePerson person) {
        return new Member(user, person);
    }

    public static Person createTrainer(User user, CreatePerson person) {
        return new Trainer(user, person);
    }

    public static Person createOwner(User user, CreatePerson person) {
        return new Owner(user, person);
    }

    public static Person createEmployee(User user, CreatePerson person) {
        return new Employee(user, person);
    }

}
