package pl.danel.gymex.domain.person.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.danel.gymex.domain.asserts.InvalidArgumentException;
import pl.danel.gymex.domain.person.Person;
import pl.danel.gymex.domain.person.user.command.CreateTechnicalUser;
import pl.danel.gymex.domain.person.user.command.CreateUser;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "SEQ_USER", allocationSize = 1)
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Person person;

    @CreationTimestamp
    private LocalDateTime createdDate;

    private String username;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "PASSWORD"))
    private Password password;

    private String email;

    private boolean active;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private User(String username, String email, Role role) {
        this.username = username;
        this.email = email;
        this.role = role;
        //TODO Activate based on activation email
        this.active = true;
    }

    private User(CreateUser command) {
        this(command.getUsername(), command.getEmail(), Role.MEMBER);
        this.person = Person.createMember(this, command.getPerson());
    }

    private User(CreateTechnicalUser command) {
        this(command.getUsername(), command.getEmail(), command.getRole());
        switch (command.getRole()) {
            case OWNER:
                this.person = Person.createOwner(this, command.getPerson());
                break;
            case EMPLOYEE:
                this.person = Person.createEmployee(this, command.getPerson());
                break;
            case MEMBER:
                throw new InvalidArgumentException("Cannot create technical account for MEMBER");
            case TRAINER:
                this.person = Person.createTrainer(this, command.getPerson());
                break;
        }
    }

    public static User createMember(CreateUser command) {
        return new User(command);
    }

    public static User createTechnical(CreateTechnicalUser createTechnicalUser) {
        return new User(createTechnicalUser);
    }

    public void createPassword(String value, PasswordEncoder encoder) {
        this.password = Password.of(value, encoder);
    }

}
