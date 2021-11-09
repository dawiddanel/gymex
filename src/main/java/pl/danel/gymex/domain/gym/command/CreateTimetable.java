package pl.danel.gymex.domain.gym.command;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class CreateTimetable {
    private LocalDate startDate;
}
