package br.edu.imepac.repositories.atendimento;

import br.edu.imepac.models.atendimento.PatientCareRecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientCareRecordRepository extends JpaRepository<PatientCareRecordModel, Long> {
}
