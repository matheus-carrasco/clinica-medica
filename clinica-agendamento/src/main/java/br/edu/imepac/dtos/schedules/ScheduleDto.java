package br.edu.imepac.dtos.schedules;

import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.EmployeeModel;
import br.edu.imepac.models.agendamento.ScheduleModel;
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
