package pl.danel.gymex.domain.gym.assortment;

import liquibase.pro.packaged.A;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import pl.danel.gymex.domain.gym.Gym;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ASSORTMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PRIVATE)
public class Assortment {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Gym gym;

    @OneToMany(
            mappedBy = "assortment",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Equipment> equipments;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private Assortment(Gym gym) {
        this.gym = gym;
    }

    public static Assortment emptyAssortment(Gym gym) {
        return new Assortment(gym);
    }

    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
        equipment.setAssortment(this);
    }

    public void removeEquipment(Equipment equipment) {
        equipments.remove(equipment);
        equipment.setAssortment(null);
    }
}
