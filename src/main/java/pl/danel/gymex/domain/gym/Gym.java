package pl.danel.gymex.domain.gym;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.gym.address.Address;
import pl.danel.gymex.domain.gym.assortment.Assortment;
import pl.danel.gymex.domain.gym.command.CreateGym;
import pl.danel.gymex.domain.gym.command.UpdateGym;
import pl.danel.gymex.domain.gym.timetable.Timetable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GYM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Gym extends AbstractAggregateRoot<Gym> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gym_sequence")
    @SequenceGenerator(name = "gym_sequence", sequenceName = "SEQ_GYM", allocationSize = 1)
    private Long id;

    @OneToOne(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private Assortment assortment;

    @OneToMany(
            mappedBy = "gym",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Timetable> timetables;

    private String name;

    private Integer capacity;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "ADR_COUNTRY")),
            @AttributeOverride(name = "city", column = @Column(name = "ADR_CITY")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "ADR_POSTAL_CODE")),
            @AttributeOverride(name = "street", column = @Column(name = "ADR_STREET")),
            @AttributeOverride(name = "buildingNumber", column = @Column(name = "ADR_BUILDING_NUMBER"))
    })
    private Address address;

    private Gym(CreateGym command) {
        this.name = command.getName();
        this.capacity = command.getCapacity();
        this.assortment = Assortment.emptyAssortment(this);
        this.timetables = new ArrayList<>(List.of(Timetable.emptyTimetable(this, command.getCreateTimetable().getStartDate())));
        this.address = Address.create(command.getAddress());
    }

    public static Gym create(CreateGym command) {
        return new Gym(command);
    }

    public void update(UpdateGym command) {
        this.name = command.getName();
        this.capacity = command.getCapacity();
        this.address = Address.create(command.getAddress());
    }

    public void addEmptyTimetable() {
        Timetable actualTimetable = actualTimetable();
        Timetable newTimetable = Timetable.emptyTimetable(this, actualTimetable.getEndDate().plusDays(1));
        timetables.add(newTimetable);
        actualTimetable.markInactive();
    }

    public Timetable actualTimetable() {
        return timetables.stream().filter(Timetable::actual).findAny()
                .orElseThrow(() -> new InvalidStateException("no actual TIMETABLE present"));
    }

    public boolean timetableOverdue() {
        return this.timetables.stream().noneMatch(Timetable::actual);
    }

}
