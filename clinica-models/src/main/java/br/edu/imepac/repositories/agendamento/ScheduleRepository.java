package br.edu.imepac.repositories.agendamento;

import br.edu.imepac.models.agendamento.ScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleModel, Long> {
}
