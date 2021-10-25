package pl.danel.gymex.domain.person;

import pl.danel.gymex.domain.common.PersonType;
import pl.danel.gymex.domain.person.user.User;
import pl.danel.gymex.domain.person.user.command.CreateUserCommand;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type",
        discriminatorType = DiscriminatorType.STRING)
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

    public Person() {
        this.personType = PersonType.MEMBER;
    }

    public static Person createEmpty() {
        return new Person();
    }

}
