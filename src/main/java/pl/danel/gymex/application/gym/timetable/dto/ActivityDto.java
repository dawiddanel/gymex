package pl.danel.gymex.application.gym.timetable.dto;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.application.person.dto.MemberDto;
import pl.danel.gymex.application.person.dto.TrainerDto;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class ActivityDto {
    private Long id;
    private ActivityDefinitionDto definition;
    private TrainerDto trainer;
    private List<MemberDto> participants;
    private List<AttendanceDto> attendance;
    private LocalDateTime startTime;
    private Integer duration;
    private Integer capacity;
}
