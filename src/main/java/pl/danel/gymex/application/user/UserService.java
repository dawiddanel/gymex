package pl.danel.gymex.application.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.security.command.RegisterCommand;
import pl.danel.gymex.adapters.rest.resource.user.command.CreateTechnicalUserCommand;
import pl.danel.gymex.application.user.dto.UserDto;
import pl.danel.gymex.application.user.mapper.UserCommandMapper;
import pl.danel.gymex.application.user.mapper.UserMapper;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.person.user.User;
import pl.danel.gymex.domain.person.user.UserRepository;
import pl.danel.gymex.domain.person.user.command.CreateTechnicalUser;
import pl.danel.gymex.domain.person.user.command.CreateUser;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserCommandMapper commandMapper;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    @Transactional
    public UserDto createUser(RegisterCommand command) {
        if (userRepository.findByUsername(command.getUsername()).isPresent()) {
            throw new InvalidStateException("username already taken");
        }
        CreateUser createUser = commandMapper.createUserCommand(command);
        User user = User.createMember(createUser);
        user.createPassword(createUser.getPassword(), encoder);
        user = userRepository.save(user);
        return mapper.map(user);
    }

    @Transactional
    public UserDto createTechnicalUser(CreateTechnicalUserCommand command) {
        if (userRepository.findByUsername(command.getUsername()).isPresent()) {
            throw new InvalidStateException("username already taken");
        }
        CreateTechnicalUser createTechnicalUser = commandMapper.createTechnicalUser(command);
        User user = User.createTechnical(createTechnicalUser);
        user.createPassword(createTechnicalUser.getPassword(), encoder);
        user = userRepository.save(user);
        return mapper.map(user);
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        String username = user.getUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(NoSuchElementException::new);
    }

    public UserDto getCurrent() {
        return mapper.map(getCurrentUser());
    }

}
