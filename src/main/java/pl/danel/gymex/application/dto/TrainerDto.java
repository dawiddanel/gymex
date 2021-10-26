package pl.danel.gymex.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrainerDto {
    private String description;
    private List<ActivityDto> activities;
}
