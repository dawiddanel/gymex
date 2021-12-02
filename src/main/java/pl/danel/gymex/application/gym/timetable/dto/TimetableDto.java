package pl.danel.gymex.application.gym.timetable.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class TimetableDto {
    private Long id;
    private List<ActivityDto> activities;
    private LocalDateTime updateDate;
    private LocalDate startDate;
    private LocalDate endDate;
}
