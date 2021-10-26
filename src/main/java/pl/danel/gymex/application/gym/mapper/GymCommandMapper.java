package pl.danel.gymex.application.gym.mapper;

import org.springframework.stereotype.Component;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateAddressCommand;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateGymCommand;
import pl.danel.gymex.adapters.rest.resource.gym.command.UpdateGymCommand;
import pl.danel.gymex.domain.gym.command.CreateAddress;
import pl.danel.gymex.domain.gym.command.CreateGym;
import pl.danel.gymex.domain.gym.command.UpdateGym;

@Component
public class GymCommandMapper {

    public CreateGym createGymCommand(CreateGymCommand command) {
        return CreateGym.builder()
                .name(command.getName())
                .squareMeters(command.getSquareMeters())
                .address(createAddress(command.getAddress()))
                .build();
    }

    public UpdateGym updateGymCommand(UpdateGymCommand command) {
        return UpdateGym.builder()
                .name(command.getName())
                .squareMeters(command.getSquareMeters())
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


}
