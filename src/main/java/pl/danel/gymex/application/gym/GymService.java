package pl.danel.gymex.application.gym;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateGymCommand;
import pl.danel.gymex.application.gym.dto.GymDto;
import pl.danel.gymex.application.gym.mapper.GymCommandMapper;
import pl.danel.gymex.application.gym.mapper.GymMapper;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.gym.GymRepository;
import pl.danel.gymex.domain.gym.command.CreateGym;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GymService {

    private final GymRepository gymRepository;

    private final GymMapper gymMapper;
    private final GymCommandMapper gymCommandMapper;

    @Transactional
    public GymDto createGym(CreateGymCommand createGymCommand) {
        CreateGym createGym = gymCommandMapper.createGymCommand(createGymCommand);
        Gym gym = Gym.create(createGym);
        gym = gymRepository.save(gym);
        return gymMapper.map(gym);
    }

    public List<GymDto> allGyms() {
        return gymRepository.findAll().stream()
                .map(gymMapper::map)
                .collect(Collectors.toList());
    }

}
