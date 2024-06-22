package br.edu.imepac.dtos.records;

import br.edu.imepac.models.agendamento.PatientModel;
import lombok.Data;

@Data
public class PatientCareRecordCreateRequest {

    private PatientModel patient;
    private String historyDescription;
    private String prescription;
    private String examRequest;
}
