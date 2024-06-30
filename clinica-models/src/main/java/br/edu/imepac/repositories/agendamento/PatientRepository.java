package br.edu.imepac.repositories;

import br.edu.imepac.models.agendamento.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {

    List<PatientModel> findByNameContainingIgnoreCase(String name);

    Optional<PatientModel> findByRgNumberIgnoreCase(String rgNumber);

    Optional<PatientModel> findByCpfNumberIgnoreCase(String cpfNumber);
}
