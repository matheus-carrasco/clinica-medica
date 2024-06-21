package br.edu.imepac.models.administrativo;

import br.edu.imepac.models.agendamento.ScheduleModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String idNumber;
    private String CpfNumber;
    private String adress;
    private String city;
    private String state;
    private String phone;
    private String workPermitNumber;
    private String pisNumber;
    private Date dateOfBirth;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<ScheduleModel> schedules;
}
