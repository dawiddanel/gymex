package pl.danel.gymex.domain.gym;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import pl.danel.gymex.domain.gym.address.Address;
import pl.danel.gymex.domain.gym.assortment.Assortment;
import pl.danel.gymex.domain.gym.timetable.Timetable;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private Timetable timetable;

    private String name;

    private Integer squareMeters;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "ADR_COUNTRY")),
            @AttributeOverride(name = "city", column = @Column(name = "ADR_CITY")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "ADR_POSTAL_CODE")),
            @AttributeOverride(name = "street", column = @Column(name = "ADR_STREET")),
            @AttributeOverride(name = "buildingNumber", column = @Column(name = "ADR_BUILDING_NUMBER"))
    })
    private Address address;

}
