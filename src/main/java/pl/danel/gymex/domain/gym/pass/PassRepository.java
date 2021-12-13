package pl.danel.gymex.domain.gym.pass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PassRepository extends JpaRepository<Pass, Long> {

    List<Pass> findAllByEndDateBefore(LocalDate nowDate);

}
