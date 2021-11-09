package pl.danel.gymex.application.gym;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateGymCommand;
import pl.danel.gymex.adapters.rest.resource.gym.command.UpdateGymCommand;
import pl.danel.gymex.application.gym.dto.GymDto;
import pl.danel.gymex.application.gym.mapper.GymCommandMapper;
import pl.danel.gymex.application.gym.mapper.GymMapper;
import pl.danel.gymex.domain.asserts.NotFoundException;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.gym.GymRepository;
import pl.danel.gymex.domain.gym.command.CreateGym;
import pl.danel.gymex.domain.gym.command.UpdateGym;

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

    @Transactional
    public GymDto updateGym(Long id, UpdateGymCommand command) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no such GYM present"));

        UpdateGym updateGym = gymCommandMapper.updateGymCommand(command);
        gym.update(updateGym);

        gym = gymRepository.save(gym);
        return gymMapper.map(gym);
    }

    @Transactional
    public void deleteGym(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no such GYM present"));

        gymRepository.delete(gym);
    }

    public GymDto gymById(Long id) {
        return gymRepository.findById(id)
                .map(gymMapper::map)
                .orElseThrow(() -> new NotFoundException("no such GYM present"));
    }

    public List<GymDto> allGyms() {
        return gymRepository.findAll().stream()
                .map(gymMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    @Scheduled(cron = "0 0 1 * * *")
    public void createNewTimetable() {
        List<Gym> gyms = gymRepository.findAll();

        gyms.forEach(gym -> {
            if (gym.timetableOverdue()) {
                gym.addEmptyTimetable();
                gymRepository.save(gym);
            }
        });
    }

}
