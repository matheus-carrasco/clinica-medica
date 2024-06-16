package br.edu.imepac.dtos.patients;

import lombok.Data;

import java.util.Date;

@Data
public class PatientCreateRequest {

    private String rgNumber;
    private String cpfNumber;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;
    private Date birthDay;
    private String sex;
    private Boolean hasHealthInsurance;
    private String HealthInsurance;
}
