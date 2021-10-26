package pl.danel.gymex.domain.person.user.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUser {
    private String username;
    private String password;
    private String email;
    private CreatePerson person;
}
