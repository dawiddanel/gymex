package pl.danel.gymex.domain.gym.timetable.activities.definitions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.danel.gymex.domain.common.Level;
import pl.danel.gymex.domain.gym.timetable.command.CreateActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.command.UpdateActivityDefinition;

import javax.persistence.*;

@Entity
@Table(name = "ACTIVITY_DEFINITION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class ActivityDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_def_sequence")
    @SequenceGenerator(name = "activity_def_sequence", sequenceName = "SEQ_ACTIVITY_DEF", allocationSize = 1)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Level level;

    private ActivityDefinition(CreateActivityDefinition command) {
        this.name = command.getName();
        this.description = command.getDescription();
        this.level = command.getLevel();
    }

    public static ActivityDefinition create(CreateActivityDefinition command) {
        return new ActivityDefinition(command);
    }

    public void update(UpdateActivityDefinition command) {
        this.name = command.getName();
        this.description = command.getDescription();
        this.level = command.getLevel();
    }

}
