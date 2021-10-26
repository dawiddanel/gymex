package pl.danel.gymex.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PassDto {
    private LocalDate startDate;
    private LocalDate activeStartDate;
    private LocalDate endDate;
}
