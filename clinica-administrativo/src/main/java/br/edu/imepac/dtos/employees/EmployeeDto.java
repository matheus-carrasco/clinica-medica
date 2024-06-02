package br.edu.imepac.dtos.employees;

import br.edu.imepac.models.EmployeeModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    private String name;
    private String adress;
    private String city;
    private String state;
    private String phone;
    private Date dateOfBirth;

    public EmployeeDto(EmployeeModel model){
        this.id = model.getId();
        this.name = model.getName();
        this.adress = model.getAdress();
        this.city = model.getCity();
        this.state = model.getState();
        this.phone = model.getPhone();
        this.dateOfBirth = model.getDateOfBirth();
    }
}
