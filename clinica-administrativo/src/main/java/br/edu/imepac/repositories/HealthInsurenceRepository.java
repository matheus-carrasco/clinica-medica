package br.edu.imepac.repositories;

import br.edu.imepac.models.HealthInsuranceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthInsurenceRepository extends JpaRepository<HealthInsuranceModel, Long> {
}
