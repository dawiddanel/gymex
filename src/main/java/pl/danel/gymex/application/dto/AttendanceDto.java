package pl.danel.gymex.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceDto {
    private MemberDto member;
    private boolean attended;
}
