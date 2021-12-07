package pl.danel.gymex.domain.gym.presence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.danel.gymex.domain.gym.Gym;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {

    List<Presence> findAllByGymAndEndTimeIsNull(Gym gym);

}
