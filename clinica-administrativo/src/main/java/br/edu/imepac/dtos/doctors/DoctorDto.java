package br.edu.imepac.dtos.doctors;

import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.SpecialtyModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    private Long id;
    private String name;
    private String crm;
    private List<SpecialtyModel> specialties;

    public DoctorDto(DoctorModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.crm = model.getCrm();
        this.specialties = model.getSpecialties();
    }
}
