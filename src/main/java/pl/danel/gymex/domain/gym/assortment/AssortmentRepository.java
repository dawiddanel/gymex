package pl.danel.gymex.domain.gym.assortment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssortmentRepository extends JpaRepository<Assortment, Long> {
}
