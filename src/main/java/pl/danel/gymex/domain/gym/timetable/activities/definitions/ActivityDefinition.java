package pl.danel.gymex.domain.gym.timetable.activities.definitions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.danel.gymex.domain.asserts.DomainAsserts;
import pl.danel.gymex.domain.common.Description;
import pl.danel.gymex.domain.common.Level;
import pl.danel.gymex.domain.common.Name;
import pl.danel.gymex.domain.gym.timetable.command.CreateActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.command.UpdateActivityDefinition;

import javax.persistence.*;

@Entity
@Table(name = "ACTIVITY_DEFINITION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ActivityDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_def_sequence")
    @SequenceGenerator(name = "activity_def_sequence", sequenceName = "SEQ_ACTIVITY_DEF", allocationSize = 1, initialValue = 100)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "NAME"))
    private Name name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "DESCRIPTION"))
    private Description description;

    @Enumerated(EnumType.STRING)
    private Level level;

    private ActivityDefinition(CreateActivityDefinition command) {
        this.name = Name.of(command.getName());
        this.description = Description.of(command.getDescription());
        this.level = command.getLevel();
    }

    public static ActivityDefinition create(CreateActivityDefinition command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        return new ActivityDefinition(command);
    }

    public void update(UpdateActivityDefinition command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        this.name = Name.of(command.getName());
        this.description = Description.of(command.getDescription());
        this.level = command.getLevel();
    }

}
