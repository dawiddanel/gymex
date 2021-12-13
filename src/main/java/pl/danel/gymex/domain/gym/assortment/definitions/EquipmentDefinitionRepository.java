package pl.danel.gymex.domain.gym.assortment.definitions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentDefinitionRepository extends JpaRepository<EquipmentDefinition, Long> {
}
