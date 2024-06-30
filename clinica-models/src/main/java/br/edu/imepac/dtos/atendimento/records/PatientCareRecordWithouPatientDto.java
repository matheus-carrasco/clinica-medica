package br.edu.imepac.dtos.records;

import br.edu.imepac.dtos.doctors.DoctorWithoutSpecialtiesDto;
import br.edu.imepac.models.atendimento.PatientCareRecordModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PatientCareRecordWithouPatientDto {

    private Long id;
    private DoctorWithoutSpecialtiesDto doctor;
    private String historyDescription;
    private String prescription;
    private String examRequest;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public PatientCareRecordWithouPatientDto(PatientCareRecordModel model){
        this.id = model.getId();
        this.doctor = new DoctorWithoutSpecialtiesDto(model.getDoctor());
        this.historyDescription = model.getHistoryDescription();
        this.prescription = model.getPrescription();
        this.examRequest = model.getExamRequest();
        this.createdAt = model.getCreatedAt();
    }
}
