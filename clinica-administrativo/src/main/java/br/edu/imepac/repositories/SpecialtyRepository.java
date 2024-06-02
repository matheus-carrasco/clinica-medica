package br.edu.imepac.repositories;

import br.edu.imepac.models.SpecialtyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialtyRepository  extends JpaRepository<SpecialtyModel, Long> {
}
