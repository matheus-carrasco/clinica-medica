package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class PatientCareRecordCreateRequest {

    private PatientModel patient;
    private String historyDescription;
    private String prescription;
    private String examRequest;
}
