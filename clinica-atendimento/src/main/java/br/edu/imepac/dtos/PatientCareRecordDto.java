package br.edu.imepac.dtos;

import br.edu.imepac.models.PatientCareRecordModel;
import lombok.Data;

@Data
public class PatientCareRecordDto {

    private Long id;
    private String historyDescription;
    private String prescription;
    private String examRequest;

    public PatientCareRecordDto(PatientCareRecordModel model){
        this.id = model.getId();
        this.historyDescription = model.getHistoryDescription();
        this.prescription = model.getPrescription();
        this.examRequest = model.getExamRequest();
    }
}
