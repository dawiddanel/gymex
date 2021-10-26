package pl.danel.gymex.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto extends PersonDto {
    private PassDto pass;
}
