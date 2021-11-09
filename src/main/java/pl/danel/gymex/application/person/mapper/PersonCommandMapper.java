package pl.danel.gymex.application.person.mapper;

import org.springframework.stereotype.Component;
import pl.danel.gymex.adapters.rest.resource.person.command.CreatePassCommand;
import pl.danel.gymex.domain.gym.command.CreatePass;

@Component
public class PersonCommandMapper {

    public CreatePass createPass(CreatePassCommand command) {
        return CreatePass.builder()
                .startDate(command.getStartDate())
                .endDate(command.getEndDate())
                .build();
    }

}
