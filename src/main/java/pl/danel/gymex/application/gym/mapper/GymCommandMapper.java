package pl.danel.gymex.application.gym.mapper;

import org.springframework.stereotype.Component;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateAddressCommand;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateGymCommand;
import pl.danel.gymex.adapters.rest.resource.gym.command.UpdateGymCommand;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.gym.command.*;
import pl.danel.gymex.domain.person.member.Member;

import java.time.LocalDate;

@Component
public class GymCommandMapper {

    public CreateGym createGymCommand(CreateGymCommand command) {
        return CreateGym.builder()
                .name(command.getName())
                .capacity(command.getCapacity())
                .address(createAddress(command.getAddress()))
                .createTimetable(createTimetable(command.getTimetableStartDate(), command.getTimetableEndDate()))
                .build();
    }

    public UpdateGym updateGymCommand(UpdateGymCommand command) {
        return UpdateGym.builder()
                .name(command.getName())
                .capacity(command.getCapacity())
                .address(createAddress(command.getAddress()))
                .build();
    }

    private CreateAddress createAddress(CreateAddressCommand command) {
        return CreateAddress.builder()
                .country(command.getCountry())
                .city(command.getCity())
                .postalCode(command.getPostalCode())
                .street(command.getStreet())
                .buildingNumber(command.getBuildingNumber())
                .build();
    }

    public CreateTimetable createTimetable(LocalDate timetableStartDate, LocalDate timetableEndDate) {
        return CreateTimetable.builder()
                .startDate(timetableStartDate)
                .endDate(timetableEndDate)
                .build();
    }

    public CreatePresence createPresence(Member member) {
        return CreatePresence.builder()
                .member(member)
                .build();
    }


}
