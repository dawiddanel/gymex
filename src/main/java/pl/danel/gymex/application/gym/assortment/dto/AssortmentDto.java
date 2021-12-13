package pl.danel.gymex.application.gym.assortment.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class AssortmentDto {
    private Long id;
    private List<EquipmentDto> equipment;
    private LocalDateTime updateDate;
}
