package pl.danel.gymex.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.danel.gymex.domain.asserts.InvalidArgumentException;
import pl.danel.gymex.domain.user.command.CreateUserCommand;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

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

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

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

    private User(CreateUserCommand command) {
        this.firstName = command.getFirstName();
        this.lastName = command.getLastName();
        this.birthDate = command.getBirthDate();
        this.username = command.getUsername();
        this.email = command.getEmail();
        this.role = mapRole(command.getRole());
        //TODO Activate based on activation email
        this.active = true;
    }

    public static User create(CreateUserCommand command) {
        return new User(command);
    }

    public void createPassword(String value, PasswordEncoder encoder) {
        this.password = Password.of(value, encoder);
    }

    private Role mapRole(String role) {
        return Arrays.stream(Role.values())
                .filter(r -> r.name().equals(role))
                .findAny()
                .orElseThrow(() -> new InvalidArgumentException("No such ROLE parameter"));
    }

}
