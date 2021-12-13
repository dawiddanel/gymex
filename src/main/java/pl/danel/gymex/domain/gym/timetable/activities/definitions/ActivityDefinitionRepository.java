package pl.danel.gymex.domain.gym.timetable.activities.definitions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityDefinitionRepository extends JpaRepository<ActivityDefinition, Long> {
}
