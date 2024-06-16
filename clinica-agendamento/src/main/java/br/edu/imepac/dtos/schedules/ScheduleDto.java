package br.edu.imepac.dtos.schedules;

import br.edu.imepac.models.ScheduleModel;
import lombok.Data;

import java.util.Date;
@Data
public class ScheduleDto {

    private Long id;
    private Long doctorId;
    private Long employeeId;
    private Date date;

    public ScheduleDto(ScheduleModel model){
        this.id = model.getId();
        this.doctorId = model.getDoctorId();
        this.employeeId = model.getEmployeeId();
        this.date = model.getDate();
    }
}
