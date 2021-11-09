package pl.danel.gymex.domain.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.danel.gymex.domain.person.trainer.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
