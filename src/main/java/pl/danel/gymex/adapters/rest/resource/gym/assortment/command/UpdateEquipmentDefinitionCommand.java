package pl.danel.gymex.adapters.rest.resource.gym.assortment.command;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateEquipmentDefinitionCommand {
    private String name;
    private String description;
    private String type;
    private String purpose;
    private Double weight;
    private List<String> aimedBodyParts;
}
