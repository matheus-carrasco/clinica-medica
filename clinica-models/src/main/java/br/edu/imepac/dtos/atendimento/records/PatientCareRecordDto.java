package br.edu.imepac.dtos.atendimento.records;

import br.edu.imepac.dtos.administrativo.doctors.DoctorDto;

import br.edu.imepac.dtos.administrativo.doctors.DoctorWithoutSpecialtiesDto;
import br.edu.imepac.dtos.agendamento.patients.PatientDto;
import br.edu.imepac.dtos.agendamento.patients.PatientWithoutCareRecordsDto;
import br.edu.imepac.models.atendimento.PatientCareRecordModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PatientCareRecordDto {

    private Long id;
    private PatientWithoutCareRecordsDto patient;
    private DoctorWithoutSpecialtiesDto doctor;
    private String historyDescription;
    private String prescription;
    private String examRequest;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public PatientCareRecordDto(PatientCareRecordModel model){
        this.id = model.getId();
        this.patient = model.getPatient() != null ? new PatientWithoutCareRecordsDto(model.getPatient()) : null;
        this.doctor = model.getDoctor() != null ? new DoctorWithoutSpecialtiesDto(model.getDoctor()) : null;
        this.historyDescription = model.getHistoryDescription();
        this.prescription = model.getPrescription();
        this.examRequest = model.getExamRequest();
        this.createdAt = model.getCreatedAt();
    }
}
