package br.edu.imepac.dtos.schedules;

import br.edu.imepac.models.DoctorModel;
import br.edu.imepac.models.EmployeeModel;
import br.edu.imepac.models.PatientModel;
import br.edu.imepac.models.ScheduleModel;
import lombok.Data;

import java.util.Date;
@Data
public class ScheduleDto {

    private Long id;
    private PatientModel patient;
    private DoctorModel doctor;
    private EmployeeModel employee;
    private Date date;

    public ScheduleDto(ScheduleModel model){
        this.id = model.getId();
        this.patient = model.getPatient();
        this.doctor = model.getDoctor();
        this.employee = model.getEmployee();
        this.date = model.getDate();
    }
}
