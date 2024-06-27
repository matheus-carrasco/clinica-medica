package br.edu.imepac.dtos.records;

import br.edu.imepac.dtos.doctors.DoctorDto;
import br.edu.imepac.dtos.patients.PatientDto;
import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.agendamento.PatientModel;
import br.edu.imepac.models.atendimento.PatientCareRecordModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
public class PatientCareRecordDto {

    private Long id;
    private PatientDto patient;
    private DoctorDto doctor;
    private String historyDescription;
    private String prescription;
    private String examRequest;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public PatientCareRecordDto(PatientCareRecordModel model){
        this.id = model.getId();
        this.patient = new PatientDto(model.getPatient());
        this.doctor = new DoctorDto(model.getDoctor());
        this.historyDescription = model.getHistoryDescription();
        this.prescription = model.getPrescription();
        this.examRequest = model.getExamRequest();
        this.createdAt = model.getCreatedAt();
    }
}
