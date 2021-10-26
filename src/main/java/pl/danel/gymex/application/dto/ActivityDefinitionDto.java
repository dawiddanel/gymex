package pl.danel.gymex.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDefinitionDto {
    private Long id;
    private String name;
    private String description;
    private String level;
}
