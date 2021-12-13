package pl.danel.gymex.domain.gym;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import pl.danel.gymex.domain.asserts.DomainAsserts;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.common.Name;
import pl.danel.gymex.domain.gym.address.Address;
import pl.danel.gymex.domain.gym.assortment.Assortment;
import pl.danel.gymex.domain.gym.command.CreateGym;
import pl.danel.gymex.domain.gym.command.CreatePresence;
import pl.danel.gymex.domain.gym.command.UpdateGym;
import pl.danel.gymex.domain.gym.presence.Presence;
import pl.danel.gymex.domain.gym.timetable.Timetable;
import pl.danel.gymex.domain.person.member.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Entity
@Table(name = "GYM")
@NoArgsConstructor
@Getter
@Setter
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
    private List<Timetable> timetables = new ArrayList<>();

    @OneToMany(
            mappedBy = "gym",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Presence> presences = new ArrayList<>();

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "NAME"))
    private Name name;

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
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        this.name = Name.of(command.getName());
        this.capacity = command.getCapacity();
        this.assortment = Assortment.emptyAssortment(this);
        this.timetables = new ArrayList<>(List.of(Timetable.emptyTimetable(this, command.getCreateTimetable().getStartDate(), command.getCreateTimetable().getEndDate())));
        this.address = Address.create(command.getAddress());
    }

    public static Gym create(CreateGym command) {
        return new Gym(command);
    }

    public void update(UpdateGym command) {
        DomainAsserts.assertArgumentNotNull(command, "command cannot be null");
        this.name = Name.of(command.getName());
        this.capacity = command.getCapacity();
        this.address = Address.create(command.getAddress());
    }

    public void addEmptyTimetable() {
        Timetable actualTimetable = actualTimetable();
        Timetable newTimetable = Timetable.emptyTimetable(this, actualTimetable.getEndDate().plusDays(1), actualTimetable.getOrderNumber() + 1);
        timetables.add(newTimetable);
    }

    public Timetable timetableById(Long id) {
        return timetables.stream().filter(timetable -> timetable.getId().equals(id)).findAny()
                .orElseThrow(() -> new InvalidStateException("no TIMETABLE with ID present"));
    }

    public Timetable actualTimetable() {
        return timetables.stream().filter(Timetable::actual).findAny()
                .orElseThrow(() -> new InvalidStateException("no actual TIMETABLE present"));
    }

    public boolean timetableOverdue() {
        return this.timetables.stream().noneMatch(Timetable::actual);
    }

    public void addPresence(CreatePresence command) {
        List<Presence> userPresences = presenceByMember(command.getMember());
        if (userPresences.stream().anyMatch(Predicate.not(Presence::finished))) {
            throw new InvalidStateException("User already present on Gym");
        }
        Presence presence = Presence.create(this, command.getMember());
        command.getMember().addPresence(presence);
    }

    public Presence presenceById(Long id) {
        return presences.stream().filter(presence -> presence.getId().equals(id)).findAny()
                .orElseThrow(() -> new InvalidStateException("no PRESENCE with ID present"));
    }

    public List<Presence> presenceByMember(Member member) {
        return presences.stream().filter(presence -> presence.getMember().equals(member)).collect(Collectors.toList());
    }

}
