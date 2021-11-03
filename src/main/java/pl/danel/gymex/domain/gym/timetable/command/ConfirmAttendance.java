package pl.danel.gymex.domain.gym.timetable.command;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.domain.person.member.Member;

@Builder
@Getter
public class ConfirmAttendance {
    private Member member;
}
