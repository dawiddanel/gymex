package pl.danel.gymex.adapters.rest.resource.person;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.danel.gymex.adapters.rest.resource.person.command.CreatePassCommand;
import pl.danel.gymex.application.person.PersonService;
import pl.danel.gymex.application.person.dto.*;

import java.util.List;

@RestController
@RequestMapping(value = "person", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PersonResource {

    private final PersonService personService;

    @GetMapping("/member/all")
    public List<MemberDto> allMembers() {
        return personService.allMembers();
    }

    @GetMapping("/owner/all")
    public List<OwnerDto> allOwners() {
        return personService.allOwners();
    }

    @GetMapping("/employee/all")
    public List<EmployeeDto> allEmployees() {
        return personService.allEmployees();
    }

    @GetMapping("/trainer/all")
    public List<TrainerDto> allTrainers() {
        return personService.allTrainers();
    }

    @GetMapping
    public PersonDto getCurrentPerson() {
        return personService.getCurrentPerson();
    }

    @PostMapping("/member/pass")
    public PassDto createPass(@RequestBody CreatePassCommand command) {
        return personService.createPass(command);
    }

}
