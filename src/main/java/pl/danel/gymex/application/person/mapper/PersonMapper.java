package pl.danel.gymex.application.person.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.danel.gymex.application.person.dto.*;
import pl.danel.gymex.domain.gym.pass.Pass;
import pl.danel.gymex.domain.person.Employee;
import pl.danel.gymex.domain.person.Owner;
import pl.danel.gymex.domain.person.Person;
import pl.danel.gymex.domain.person.member.Member;
import pl.danel.gymex.domain.person.trainer.Trainer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {

    public List<EmployeeDto> employees(List<Employee> employees) {
        if (CollectionUtils.isEmpty(employees)) {
            return null;
        }
        return employees.stream()
                .map(this::employee)
                .collect(Collectors.toList());
    }

    public EmployeeDto employee(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDto result = new EmployeeDto();
        mapBasicPersonData(employee, result);
        return result;
    }

    public List<MemberDto> members(List<Member> members) {
        if (CollectionUtils.isEmpty(members)) {
            return Collections.emptyList();
        }
        return members.stream()
                .map(this::member)
                .collect(Collectors.toList());
    }

    public MemberDto member(Member member) {
        if (member == null) {
            return null;
        }
        MemberDto result = new MemberDto();
        mapBasicPersonData(member, result);
        result.setPass(pass(member.actualPass().orElse(null)));
        return result;
    }

    public PassDto pass(Pass pass) {
        if (pass == null) {
            return null;
        }
        return PassDto.builder()
                .startDate(pass.getStartDate())
                .endDate(pass.getEndDate())
                .build();
    }

    public List<OwnerDto> owners(List<Owner> owners) {
        if (CollectionUtils.isEmpty(owners)) {
            return null;
        }
        return owners.stream()
                .map(this::owner)
                .collect(Collectors.toList());
    }

    public OwnerDto owner(Owner owner) {
        if (owner == null) {
            return null;
        }
        OwnerDto result = new OwnerDto();
        mapBasicPersonData(owner, result);
        return result;
    }

    public List<TrainerDto> trainers(List<Trainer> trainers) {
        if (CollectionUtils.isEmpty(trainers)) {
            return null;
        }
        return trainers.stream()
                .map(this::trainer)
                .collect(Collectors.toList());
    }

    public TrainerDto trainer(Trainer trainer) {
        if (trainer == null) {
            return null;
        }
        TrainerDto result = new TrainerDto();
        mapBasicPersonData(trainer, result);
        return result;
    }

    private void mapBasicPersonData(Person person, PersonDto result) {
        result.setId(person.getId());
        result.setFirstName(person.getFirstName() != null ? person.getFirstName().getValue() : null);
        result.setLastName(person.getLastName() != null ? person.getLastName().getValue() : null);
        result.setBirthDate(person.getBirthDate());
        result.setPesel(person.getPesel() != null ? person.getPesel().getValue() : null);
    }


}
