package pl.danel.gymex.application.gym.mapper;

import org.springframework.stereotype.Component;
import pl.danel.gymex.application.common.dto.AddressDto;
import pl.danel.gymex.application.gym.dto.GymDto;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.gym.address.Address;

@Component
public class GymMapper {

    public GymDto map(Gym gym) {
        return GymDto.builder()
                .id(gym.getId())
                .name(gym.getName())
                .squareMeters(gym.getSquareMeters())
                .address(address(gym.getAddress()))
                .build();
    }

    private AddressDto address(Address address) {
        if (address == null) {
            return null;
        }
        return AddressDto.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .street(address.getStreet())
                .buildingNumber(address.getBuildingNumber())
                .build();
    }

}
