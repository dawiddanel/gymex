package pl.danel.gymex.domain.gym.command;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class CreatePass {
    private LocalDate startDate;
    private LocalDate endDate;
}
