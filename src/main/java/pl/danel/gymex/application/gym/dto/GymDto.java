package pl.danel.gymex.application.gym.dto;

import lombok.Builder;
import lombok.Getter;
import pl.danel.gymex.application.common.dto.AddressDto;

@Builder
@Getter
public class GymDto {
    private Long id;
    private String name;
    private Integer squareMeters;
    private AddressDto address;
}
