package br.edu.imepac.dtos.specialty;

import br.edu.imepac.dtos.doctors.DoctorWithoutSpecialtiesDto;
import br.edu.imepac.models.administrativo.SpecialtyModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SpecialtyWithDoctorsDto {

    private Long id;
    private String description;
    private List<DoctorWithoutSpecialtiesDto> doctors;

    public SpecialtyWithDoctorsDto(SpecialtyModel model){
        this.id = model.getId();
        this.description = model.getDescription();
        this.doctors = model.getDoctors().stream()
                .map(DoctorWithoutSpecialtiesDto::new)
                .collect(Collectors.toList());
    }
}
