package br.edu.imepac.repositories.administrativo;

import br.edu.imepac.models.administrativo.HealthInsuranceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthInsuranceRepository extends JpaRepository<HealthInsuranceModel, Long> {

    List<HealthInsuranceModel> findByNameContainingIgnoreCase(String name);

    Optional<HealthInsuranceModel> findByCnpjIgnoreCase(String cnpj);
}
