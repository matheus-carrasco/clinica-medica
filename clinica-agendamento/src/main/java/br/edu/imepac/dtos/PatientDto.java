package br.edu.imepac.dtos;

import br.edu.imepac.models.PatientModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

    private Long id;
    private String name;
    private String city;
    private String state;
    private String phoneNumber;
    private boolean hasHealthInsurance;
    private String healthInsurance;

    public PatientDto(PatientModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.city = model.getCity();
        this.state = model.getState();
        this.phoneNumber = model.getPhoneNumber();
        this.hasHealthInsurance = model.isHasHealthInsurance();
        this.healthInsurance = model.getHealthInsurance();
    }

}
