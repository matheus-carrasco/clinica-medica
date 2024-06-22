package br.edu.imepac.dtos.records;

import br.edu.imepac.models.agendamento.PatientModel;
import br.edu.imepac.models.atendimento.PatientCareRecordModel;
import lombok.Data;

@Data
public class PatientCareRecordDto {

    private Long id;
    private PatientModel patient;
    private String historyDescription;
    private String prescription;
    private String examRequest;

    public PatientCareRecordDto(PatientCareRecordModel model){
        this.id = model.getId();
        this.patient = model.getPatient();
        this.historyDescription = model.getHistoryDescription();
        this.prescription = model.getPrescription();
        this.examRequest = model.getExamRequest();
    }
}
