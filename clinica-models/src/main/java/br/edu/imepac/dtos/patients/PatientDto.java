package br.edu.imepac.dtos.patients;

import br.edu.imepac.models.agendamento.PatientModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PatientDto {

    private Long id;
    private String name;
    private String city;
    private String state;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;
    private String sex;
    private Boolean hasHealthInsurance;
    private Long healthInsuranceId;

    public PatientDto(PatientModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.city = model.getCity();
        this.state = model.getState();
        this.birthDay = model.getBirthDay();
        this.sex = model.getSex();
        this.hasHealthInsurance = model.getHasHealthInsurance();
        this.healthInsuranceId = model.getHealthInsurance() != null ? model.getHealthInsurance().getId() : null;
    }
}
