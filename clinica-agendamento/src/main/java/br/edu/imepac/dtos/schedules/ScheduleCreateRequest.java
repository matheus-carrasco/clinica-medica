package br.edu.imepac.dtos.schedules;

import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.EmployeeModel;
import lombok.Data;

import java.util.Date;
@Data
public class ScheduleCreateRequest {

    private PatientModel patient;
    private DoctorModel doctor;
    private EmployeeModel employee;
    private Date date;
}
