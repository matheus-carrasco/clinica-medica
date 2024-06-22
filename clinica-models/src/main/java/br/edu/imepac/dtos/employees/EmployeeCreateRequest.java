package br.edu.imepac.dtos.employees;

import lombok.Data;

import java.util.Date;
@Data
public class EmployeeCreateRequest {

    private String name;
    private String idNumber;
    private String CpfNumber;
    private String adress;
    private String city;
    private String state;
    private String phone;
    private String workPermitNumber;
    private String pisNumber;
    private Date dateOfBirth;
}
