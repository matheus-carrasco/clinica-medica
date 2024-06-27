package br.edu.imepac.dtos.patients;

import br.edu.imepac.dtos.records.PatientCareRecordDto;
import br.edu.imepac.models.administrativo.HealthInsuranceModel;
import br.edu.imepac.models.agendamento.PatientModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private HealthInsuranceModel healthInsurance;

    public PatientDto(PatientModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.city = model.getCity();
        this.state = model.getState();
        this.birthDay = model.getBirthDay();
        this.sex = model.getSex();
        this.hasHealthInsurance = model.getHasHealthInsurance();

        if(model.getHealthInsurance() != null){
            this.healthInsurance = model.getHealthInsurance();;
        }
        else {
            this.healthInsurance = null;
        }
    }
}
