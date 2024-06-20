package br.edu.imepac.repositories;

import br.edu.imepac.models.administrativo.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
}
