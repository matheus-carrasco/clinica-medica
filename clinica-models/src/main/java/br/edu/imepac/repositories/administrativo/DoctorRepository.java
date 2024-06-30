package br.edu.imepac.repositories;

import br.edu.imepac.models.administrativo.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorModel, Long> {

    List<DoctorModel> findByNameContainingIgnoreCase(String name);
    Optional<DoctorModel> findByCrmIgnoreCase(String crm);
}
