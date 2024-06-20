package br.edu.imepac.models.agendamento;

import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.EmployeeModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@Table(name = "schedules")
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PatientModel patient;
    private DoctorModel doctor;
    private EmployeeModel employee;
    private Date date;
}
