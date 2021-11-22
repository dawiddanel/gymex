package pl.danel.gymex.application.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private String username;
    private String email;
    private String role;
    private LocalDateTime createdDate;
}
