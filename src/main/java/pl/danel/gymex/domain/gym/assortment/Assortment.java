package pl.danel.gymex.domain.gym.assortment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import pl.danel.gymex.domain.asserts.DomainAsserts;
import pl.danel.gymex.domain.asserts.NotFoundException;
import pl.danel.gymex.domain.gym.Gym;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ASSORTMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
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
    private List<Equipment> equipments = new ArrayList<>();

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private Assortment(Gym gym) {
        this.gym = gym;
    }

    public static Assortment emptyAssortment(Gym gym) {
        return new Assortment(gym);
    }

    public void addEquipment(Equipment equipment) {
        DomainAsserts.assertArgumentNotNull(equipment, "equipment cannot be null");
        equipments.add(equipment);
        equipment.setAssortment(this);
    }

    public void removeEquipment(Equipment equipment) {
        DomainAsserts.assertArgumentNotNull(equipment, "equipment cannot be null");
        equipments.remove(equipment);
        equipment.setAssortment(null);
    }

    public Equipment equipmentById(Long id) {
        DomainAsserts.assertState(id != null, "EQUIPMENT id cannot be null");
        return equipments.stream()
                .filter(equipment -> equipment.getId().equals(id))
                .findAny().orElseThrow(() -> new NotFoundException("no such EQUIPMENT present in ASSORTMENT"));
    }
}
