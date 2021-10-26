package pl.danel.gymex.application.gym.assortment.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EquipmentDto {
    private Long id;
    private EquipmentDefinitionDto definition;
    private Integer quantity;
}
