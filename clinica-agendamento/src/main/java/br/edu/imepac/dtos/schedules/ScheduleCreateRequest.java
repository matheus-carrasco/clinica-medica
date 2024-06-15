package br.edu.imepac.dtos.schedules;

import br.edu.imepac.models.DoctorModel;
import br.edu.imepac.models.EmployeeModel;
import br.edu.imepac.models.PatientModel;
import lombok.Data;

import java.util.Date;
@Data
public class ScheduleCreateRequest {

    private PatientModel patient;
    private DoctorModel doctor;
    private EmployeeModel employee;
    private Date date;
}
