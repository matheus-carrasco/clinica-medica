package br.edu.imepac.dtos.records;

import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.agendamento.PatientModel;
import lombok.Data;

import java.util.Date;

@Data
public class PatientCareRecordCreateRequest {

    private PatientModel patient;
    private DoctorModel doctor;
    private String historyDescription;
    private String prescription;
    private String examRequest;
}
