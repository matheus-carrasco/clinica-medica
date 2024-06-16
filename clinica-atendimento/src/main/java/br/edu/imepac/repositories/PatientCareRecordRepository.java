package br.edu.imepac.repositories;

import br.edu.imepac.models.PatientCareRecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientCareRecordRepository extends JpaRepository<PatientCareRecordModel, Long> {
}
