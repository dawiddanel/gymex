package pl.danel.gymex.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ActivityDto {
    private Long id;
    private List<MemberDto> participants;
    private List<AttendanceDto> attendance;
    private LocalDateTime startTime;
    private Integer duration;
    private Integer capacity;
}
