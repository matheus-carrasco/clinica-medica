package br.edu.imepac.dtos.atendimento.records;

import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.agendamento.PatientModel;
import lombok.Data;

@Data
public class PatientCareRecordCreateRequest {

    private PatientModel patient;
    private DoctorModel doctor;
    private String historyDescription;
    private String prescription;
    private String examRequest;
}
