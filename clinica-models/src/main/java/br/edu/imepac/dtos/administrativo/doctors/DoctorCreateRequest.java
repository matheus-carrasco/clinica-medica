package br.edu.imepac.dtos.doctors;


import br.edu.imepac.models.administrativo.SpecialtyModel;
import lombok.Data;

import java.util.List;

@Data
public class DoctorCreateRequest {
    private String name;
    private String crm;
    private String password;
    private List<SpecialtyModel> specialties;
}
