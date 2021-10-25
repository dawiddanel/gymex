package pl.danel.gymex.application.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.command.RegisterCommand;
import pl.danel.gymex.application.user.dto.UserDto;
import pl.danel.gymex.application.user.mapper.UserCommandMapper;
import pl.danel.gymex.application.user.mapper.UserMapper;
import pl.danel.gymex.domain.person.user.User;
import pl.danel.gymex.domain.person.user.UserRepository;
import pl.danel.gymex.domain.person.user.command.CreateUserCommand;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserCommandMapper commandMapper;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    @Transactional
    public UserDto createUser(RegisterCommand registerCommand) {
        CreateUserCommand command = commandMapper.createUserCommand(registerCommand);
        User user = User.create(command);
        user.createPassword(command.getPassword(), encoder);
        user = userRepository.save(user);
        return mapper.map(user);
    }

}
