package pl.danel.gymex.application.user.mapper;

import org.springframework.stereotype.Component;
import pl.danel.gymex.application.user.dto.UserDto;
import pl.danel.gymex.domain.person.user.User;

@Component
public class UserMapper {

    public UserDto map(User user) {
        var result = new UserDto();
        result.setUsername(user.getUsername());
        result.setEmail(user.getEmail());
        return result;
    }

}
