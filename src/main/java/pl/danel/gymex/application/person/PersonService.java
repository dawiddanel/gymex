package pl.danel.gymex.application.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.danel.gymex.adapters.rest.resource.person.command.CreatePassCommand;
import pl.danel.gymex.application.person.dto.*;
import pl.danel.gymex.application.person.mapper.PersonCommandMapper;
import pl.danel.gymex.application.person.mapper.PersonMapper;
import pl.danel.gymex.application.user.UserService;
import pl.danel.gymex.domain.asserts.InvalidArgumentException;
import pl.danel.gymex.domain.asserts.InvalidStateException;
import pl.danel.gymex.domain.asserts.NotFoundException;
import pl.danel.gymex.domain.gym.command.CreatePass;
import pl.danel.gymex.domain.person.*;
import pl.danel.gymex.domain.person.member.Member;
import pl.danel.gymex.domain.person.trainer.Trainer;
import pl.danel.gymex.domain.person.user.Role;
import pl.danel.gymex.domain.person.user.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final UserService userService;

    private final PersonMapper personMapper;
    private final PersonCommandMapper personCommandMapper;

    private final MemberRepository memberRepository;
    private final OwnerRepository ownerRepository;
    private final EmployeeRepository employeeRepository;
    private final TrainerRepository trainerRepository;

    public List<MemberDto> allMembers() {
        return personMapper.members(memberRepository.findAll());
    }

    public List<TrainerDto> allTrainers() {
        return personMapper.trainers(trainerRepository.findAll());
    }

    public List<EmployeeDto> allEmployees() {
        return personMapper.employees(employeeRepository.findAll());
    }

    public List<OwnerDto> allOwners() {
        return personMapper.owners(ownerRepository.findAll());
    }

    public PersonDto getCurrentPerson() {
        User user = userService.getCurrentUser();
        switch (user.getRole()) {
            case OWNER:
                Owner owner = (Owner) user.getPerson();
                return personMapper.owner(owner);
            case EMPLOYEE:
                Employee employee = (Employee) user.getPerson();
                return personMapper.employee(employee);
            case TRAINER:
                Trainer trainer = (Trainer) user.getPerson();
                return personMapper.trainer(trainer);
            case MEMBER:
                Member member = (Member) user.getPerson();
                return personMapper.member(member);
            default: throw new InvalidStateException("No such ROLE available");
        }
    }

    public Member memberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("no such MEMBER present"));
    }

    public Member currentlyLoggedMember() {
        User user = userService.getCurrentUser();
        if (Role.MEMBER.equals(user.getRole())) {
            return (Member) user.getPerson();
        }
        throw new InvalidArgumentException("currently logged person is not a MEMBER");
    }

    public PassDto createPass(CreatePassCommand command) {
        Member member = currentlyLoggedMember();

        CreatePass createPass = personCommandMapper.createPass(command);

        member.addPass(createPass);
        member = memberRepository.save(member);
        return personMapper.pass(member.actualPass().orElse(null));
    }


}
