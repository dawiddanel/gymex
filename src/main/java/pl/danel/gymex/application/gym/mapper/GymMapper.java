package pl.danel.gymex.application.gym.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pl.danel.gymex.application.gym.dto.AddressDto;
import pl.danel.gymex.application.gym.dto.GymDto;
import pl.danel.gymex.application.gym.presence.PresenceDto;
import pl.danel.gymex.application.gym.presence.PresenceMemberDto;
import pl.danel.gymex.application.gym.timetable.dto.ActivityDto;
import pl.danel.gymex.domain.gym.Gym;
import pl.danel.gymex.domain.gym.address.Address;
import pl.danel.gymex.domain.gym.presence.Presence;
import pl.danel.gymex.domain.person.member.Member;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GymMapper {

    public GymDto map(Gym gym) {
        return GymDto.builder()
                .id(gym.getId())
                .name(gym.getName() != null ? gym.getName().getValue() : null)
                .capacity(gym.getCapacity())
                .address(address(gym.getAddress()))
                .build();
    }

    private AddressDto address(Address address) {
        if (address == null) {
            return null;
        }
        return AddressDto.builder()
                .country(address.getCountry() != null ? address.getCountry().getValue() : null)
                .city(address.getCity() != null ? address.getCity().getValue() : null)
                .postalCode(address.getPostalCode() != null ? address.getPostalCode().getValue() : null)
                .street(address.getStreet() != null ? address.getStreet().getValue() : null)
                .buildingNumber(address.getBuildingNumber() != null ? address.getBuildingNumber().getValue() : null)
                .build();
    }

    public List<PresenceDto> presences(List<Presence> presence) {
        if (CollectionUtils.isEmpty(presence)) {
            return Collections.emptyList();
        }
        return presence.stream()
                .map(this::presence)
                .sorted(Comparator.comparing(PresenceDto::getStartTime, Comparator.nullsLast(Comparator.reverseOrder())))
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
                .firstName(member.getFirstName() != null ? member.getFirstName().getValue() : null)
                .lastName(member.getLastName() != null ? member.getLastName().getValue() : null)
                .build();
    }

}
