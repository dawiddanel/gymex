package pl.danel.gymex.application.gym.mapper;

import org.springframework.stereotype.Component;
import pl.danel.gymex.adapters.rest.resource.common.command.CreateAddressCommand;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateGymCommand;
import pl.danel.gymex.domain.common.command.CreateAddress;
import pl.danel.gymex.domain.gym.command.CreateGym;

@Component
public class GymCommandMapper {

    public CreateGym createGymCommand(CreateGymCommand command) {
        return CreateGym.builder()
                .name(command.getName())
                .squareMeters(command.getSquareMeters())
                .addressCommand(createAddress(command.getAddress()))
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
