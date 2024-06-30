package br.edu.imepac.repositories;

import br.edu.imepac.models.administrativo.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {

    List<EmployeeModel> findByNameContainingIgnoreCase(String name);

    Optional<EmployeeModel> findByRgNumberIgnoreCase(String rgNumber);

    Optional<EmployeeModel> findByCpfNumberIgnoreCase(String cpfNumber);
}
