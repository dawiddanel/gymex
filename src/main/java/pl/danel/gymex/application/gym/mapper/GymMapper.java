package pl.danel.gymex.application.gym.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.danel.gymex.application.gym.dto.AddressDto;
import pl.danel.gymex.application.gym.dto.GymDto;
import pl.danel.gymex.application.gym.presence.PresenceDto;
import pl.danel.gymex.application.gym.presence.PresenceMemberDto;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.gym.address.Address;
import pl.danel.gymex.domain.gym.presence.Presence;
import pl.danel.gymex.domain.person.member.Member;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GymMapper {

    public GymDto map(Gym gym) {
        return GymDto.builder()
                .id(gym.getId())
                .name(gym.getName())
                .capacity(gym.getCapacity())
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

    public List<PresenceDto> presences(List<Presence> presence) {
        if (CollectionUtils.isEmpty(presence)) {
            return Collections.emptyList();
        }
        return presence.stream()
                .map(this::presence)
                .collect(Collectors.toList());
    }

    public PresenceDto presence(Presence presence) {
        if (presence == null) {
            return null;
        }
        return PresenceDto.builder()
                .id(presence.getId())
                .member(presenceMember(presence.getMember()))
                .startTime(presence.getStartTime())
                .endTime(presence.getEndTime())
                .build();
    }

    private PresenceMemberDto presenceMember(Member member) {
        if (member == null) {
            return null;
        }
        return PresenceMemberDto.builder()
                .id(member.getId())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .build();
    }

}
