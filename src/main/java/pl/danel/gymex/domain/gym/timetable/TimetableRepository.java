package pl.danel.gymex.domain.gym.timetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.danel.gymex.domain.gym.timetable.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
