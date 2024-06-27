package br.edu.imepac.models.atendimento;

import br.edu.imepac.models.administrativo.DoctorModel;
import br.edu.imepac.models.agendamento.PatientModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "patient_care_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientCareRecordModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PatientModel patient;

    @ManyToOne
    private DoctorModel doctor;

    private String historyDescription;
    private String prescription;
    private String examRequest;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;
}
