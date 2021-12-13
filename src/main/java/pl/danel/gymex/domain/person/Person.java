package pl.danel.gymex.domain.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.danel.gymex.domain.asserts.DomainAsserts;
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
@Setter
public class Person {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false, name = "person_type")
    private PersonType personType;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "FIRSTNAME"))
    private Firstname firstName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "LASTNAME"))
    private Lastname lastName;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "PESEL"))
    private Pesel pesel;

    private LocalDate birthDate;

    public Person(User user, CreatePerson person) {
        DomainAsserts.assertArgumentNotNull(user, "user cannot be null");
        DomainAsserts.assertArgumentNotNull(person, "command cannot be null");
        this.user = user;
        this.firstName = Firstname.of(person.getFirstName());
        this.lastName = Lastname.of(person.getLastName());
        this.pesel = Pesel.of(person.getPesel());
        this.birthDate = person.getBirthDate();
    }

    public static Member createMember(User user, CreatePerson person) {
        return new Member(user, person);
    }

    public static Trainer createTrainer(User user, CreatePerson person) {
        return new Trainer(user, person);
    }

    public static Owner createOwner(User user, CreatePerson person) {
        return new Owner(user, person);
    }

    public static Employee createEmployee(User user, CreatePerson person) {
        return new Employee(user, person);
    }

}
