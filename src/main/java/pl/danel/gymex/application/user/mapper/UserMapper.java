package pl.danel.gymex.application.user.mapper;

import org.springframework.stereotype.Component;
import pl.danel.gymex.application.user.dto.UserDto;
import pl.danel.gymex.domain.person.user.Role;
import pl.danel.gymex.domain.person.user.User;

@Component
public class UserMapper {

    public UserDto map(User user) {
        var result = new UserDto();
        result.setUsername(user.getUsername() != null ? user.getUsername().getValue() : null);
        result.setEmail(user.getEmail() != null ? user.getEmail().getValue() : null);
        result.setRole(mapRole(user.getRole()));
        result.setCreatedDate(user.getCreatedDate());
        return result;
    }

    private String mapRole(Role role) {
        if (role == null) {
            return null;
        }
        return role.name();
    }

}
