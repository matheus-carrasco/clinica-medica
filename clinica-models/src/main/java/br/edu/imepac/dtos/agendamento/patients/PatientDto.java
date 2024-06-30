package br.edu.imepac.dtos.agendamento.patients;

import br.edu.imepac.dtos.administrativo.health_insurance.HealthInsuranceDto;

import br.edu.imepac.dtos.atendimento.records.PatientCareRecordWithouPatientDto;
import br.edu.imepac.models.agendamento.PatientModel;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private HealthInsuranceDto healthInsurance;
    private List<PatientCareRecordWithouPatientDto> careRecords;


    public PatientDto(PatientModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.city = model.getCity();
        this.state = model.getState();
        this.birthDay = model.getBirthDay();
        this.sex = model.getSex();
        this.hasHealthInsurance = model.getHasHealthInsurance();
        this.healthInsurance = model.getHealthInsurance() != null ? new HealthInsuranceDto(model.getHealthInsurance()) : null;
        this.careRecords = model.getCareRecords().stream().map(PatientCareRecordWithouPatientDto::new).collect(Collectors.toList());
    }
}
