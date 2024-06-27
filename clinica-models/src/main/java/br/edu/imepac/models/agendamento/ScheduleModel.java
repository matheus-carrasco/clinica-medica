package br.edu.imepac.models.agendamento;

import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.administrativo.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Entity
@Data
@Table(name = "schedules")
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientModel patient;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorModel doctor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeModel employee;

    @JsonFormat(pattern = "yyyy-DD-mm HH:mm")
    private Date date;
}
