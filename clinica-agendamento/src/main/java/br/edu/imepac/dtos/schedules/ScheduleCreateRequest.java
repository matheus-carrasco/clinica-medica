package br.edu.imepac.dtos.schedules;

import lombok.Data;

import java.util.Date;
@Data
public class ScheduleCreateRequest {

    private Long doctorId;
    private Long employeeId;
    private Date date;
}
