package br.edu.imepac.dtos.administrativo.doctors;

import br.edu.imepac.models.administrativo.DoctorModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorWithoutSpecialtiesDto {
    private Long id;
    private String name;
    private String crm;

    public DoctorWithoutSpecialtiesDto(DoctorModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.crm = model.getCrm();
    }
}
