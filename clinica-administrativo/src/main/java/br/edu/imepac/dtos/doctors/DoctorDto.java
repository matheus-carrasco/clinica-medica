package br.edu.imepac.dtos.doctors;

import br.edu.imepac.models.DoctorModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    private Long id;
    private String name;
    private String crm;

    public DoctorDto(DoctorModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.crm = model.getCrm();
    }
}
