package br.edu.imepac.dtos.schedules;

import br.edu.imepac.dtos.doctors.DoctorDto;
import br.edu.imepac.dtos.employees.EmployeeDto;
import br.edu.imepac.dtos.patients.PatientDto;
import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.EmployeeModel;
import br.edu.imepac.models.agendamento.PatientModel;
import br.edu.imepac.models.agendamento.ScheduleModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.Date;
@Data
@NoArgsConstructor
public class ScheduleDto {

    private Long id;
    private PatientDto patient;
    private DoctorDto doctor;
    private EmployeeDto employee;

    @JsonFormat(pattern = "yyyy-DD-mm HH:mm")
    private Date date;


    private static ModelMapper modelMapper = new ModelMapper();

    public ScheduleDto(ScheduleModel model){
        this.id = model.getId();
        this.patient = modelMapper.map(model.getPatient(), PatientDto.class);
        this.doctor = modelMapper.map(model.getDoctor(), DoctorDto.class);
        this.employee = modelMapper.map(model.getEmployee(), EmployeeDto.class);
        this.date = model.getDate();
    }
}
