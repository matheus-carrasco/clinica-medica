package br.edu.imepac.dtos.patients;

import br.edu.imepac.models.PatientModel;
import lombok.Data;

import java.util.Date;

@Data
public class PatientDto {

    private Long id;
    private String city;
    private String state;
    private Date birthDay;
    private String sex;
    private Boolean hasHealthInsurance;
    private String healthInsurance;

    public PatientDto(PatientModel model){
        this.id = model.getId();
        this.city = model.getCity();
        this.state = model.getState();
        this.birthDay = model.getBirthDay();
        this.sex = model.getSex();
        this.hasHealthInsurance = model.getHasHealthInsurance();
        this.healthInsurance = model.getHealthInsurance();
    }
}
