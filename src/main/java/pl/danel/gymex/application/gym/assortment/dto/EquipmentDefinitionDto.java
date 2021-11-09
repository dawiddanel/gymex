package pl.danel.gymex.application.gym.assortment.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class EquipmentDefinitionDto {
    private Long id;
    private String name;
    private String description;
    private String type;
    private String purpose;
    private Double weight;
    private List<String> aimedBodyParts;
}
