package br.edu.imepac.dtos.doctors;

import br.edu.imepac.models.SpecialtyModel;
import lombok.Data;

@Data
public class DoctorCreateRequest {
    private String name;
    private String crm;
    private String password;
    private SpecialtyModel specialty;
}
