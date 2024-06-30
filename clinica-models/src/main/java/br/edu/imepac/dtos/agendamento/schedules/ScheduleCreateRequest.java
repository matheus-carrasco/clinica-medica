package br.edu.imepac.dtos.schedules;

import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.EmployeeModel;
import br.edu.imepac.models.agendamento.PatientModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class ScheduleCreateRequest {

    private PatientModel patient;
    private DoctorModel doctor;
    private EmployeeModel employee;

    @JsonFormat(pattern = "yyyy-DD-mm HH:mm")
    private Date date;
}
