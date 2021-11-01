package pl.danel.gymex.domain.gym.assortment.command;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.domain.gym.assortment.Assortment;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinition;

@Builder
@Getter
public class CreateEquipment {
    private Integer quantity;
    private EquipmentDefinition definition;
}
