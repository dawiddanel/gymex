package pl.danel.gymex.application.gym.timetable.dto;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.application.person.dto.MemberDto;

@Builder
@Getter
public class AttendanceDto {
    private MemberDto member;
    private boolean attended;
}
