package pl.danel.gymex.domain.gym.command;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.domain.person.member.Member;

@Builder
@Getter
public class CreatePresence {
    private Member member;
}
