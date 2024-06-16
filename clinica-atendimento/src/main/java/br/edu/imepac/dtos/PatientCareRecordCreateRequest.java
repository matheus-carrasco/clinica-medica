package br.edu.imepac.dtos;

import lombok.Data;

@Data
public class PatientCareRecordCreateRequest {

    private String historyDescription;
    private String prescription;
    private String examRequest;
}
