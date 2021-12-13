package pl.danel.gymex.adapters.rest.resource.security.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInCommand {
    private String username;
    private String password;
}
