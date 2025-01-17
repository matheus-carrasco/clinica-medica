package br.edu.imepac.models.agendamento;

import br.edu.imepac.models.administrativo.HealthInsuranceModel;
import br.edu.imepac.models.atendimento.PatientCareRecordModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
public class PatientModel implements Serializable {
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
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;
    private String sex;
    private Boolean hasHealthInsurance;

    @ManyToOne
    @JoinColumn(name = "health_insurance_id", nullable = true)
    private HealthInsuranceModel healthInsurance;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<ScheduleModel> schedules;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatientCareRecordModel> careRecords = new ArrayList<>();
}