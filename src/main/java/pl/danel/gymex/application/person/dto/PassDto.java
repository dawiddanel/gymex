package pl.danel.gymex.application.person.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PassDto {
    private LocalDate startDate;
    private LocalDate activeStartDate;
    private LocalDate endDate;
}
