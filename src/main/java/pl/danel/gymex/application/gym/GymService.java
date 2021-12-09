package pl.danel.gymex.application.gym;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateGymCommand;
import pl.danel.gymex.adapters.rest.resource.gym.command.UpdateGymCommand;
import pl.danel.gymex.adapters.rest.resource.gym.presence.CreatePresenceCommand;
import pl.danel.gymex.application.gym.dto.GymDto;
import pl.danel.gymex.application.gym.mapper.GymCommandMapper;
import pl.danel.gymex.application.gym.mapper.GymMapper;
import pl.danel.gymex.application.gym.presence.PresenceDto;
import pl.danel.gymex.application.person.PersonService;
import pl.danel.gymex.domain.asserts.NotFoundException;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.gym.GymRepository;
import pl.danel.gymex.domain.gym.command.CreateGym;
import pl.danel.gymex.domain.gym.command.CreatePresence;
import pl.danel.gymex.domain.gym.command.UpdateGym;
import pl.danel.gymex.domain.gym.presence.Presence;
import pl.danel.gymex.domain.gym.presence.PresenceRepository;
import pl.danel.gymex.domain.person.member.Member;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GymService {

    private final PersonService personService;

    private final GymRepository gymRepository;
    private final PresenceRepository presenceRepository;

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

    public List<GymDto> allGymsDto() {
        return allGyms().stream()
                .map(gymMapper::map)
                .collect(Collectors.toList());
    }

    public List<Gym> allGyms() {
        return gymRepository.findAll();
    }

    @Transactional
    public void createNewTimetable(Gym gym) {
        gym.addEmptyTimetable();
        gymRepository.save(gym);
    }

    public List<PresenceDto> gymCurrentPresences(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no such GYM present"));

        List<Presence> presences = presenceRepository.findAllByGymAndEndTimeIsNull(gym);
        return presences.stream().
                map(gymMapper::presence)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createPresence(Long id, CreatePresenceCommand command) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no such GYM present"));

        Member member = personService.memberById(command.getMemberId());

        CreatePresence createPresence = gymCommandMapper.createPresence(member);
        gym.addPresence(createPresence);

        gymRepository.save(gym);
    }

    @Transactional
    public void finishMemberPresence(Long id, Long presenceId) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no such GYM present"));

        Presence presence = gym.presenceById(presenceId);
        presence.finish();

        gymRepository.save(gym);
    }

    public List<PresenceDto> findAllMemberPresences() {
        Member member = personService.currentlyLoggedMember();
        return gymMapper.presences(presenceRepository.findAllByMember(member));
    }

}
