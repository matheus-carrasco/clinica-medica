package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class PatientCreateRequest {

    private String name;
    private String rgNumber;
    private String cpfNumber;
    private String adress;
    private String city;
    private String state;
    private String phoneNumber;
    private boolean hasHealthInsurance;
    private String healthInsurance;
}
