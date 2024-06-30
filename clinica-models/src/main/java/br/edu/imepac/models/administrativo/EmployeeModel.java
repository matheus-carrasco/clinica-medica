package br.edu.imepac.models.administrativo;

import br.edu.imepac.models.agendamento.ScheduleModel;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String rgNumber;
    private String cpfNumber;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String workPermitNumber;
    private String pisNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<ScheduleModel> schedules;
}
