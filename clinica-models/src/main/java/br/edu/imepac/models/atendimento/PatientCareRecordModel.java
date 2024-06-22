package br.edu.imepac.models.atendimento;

import br.edu.imepac.models.agendamento.PatientModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "patient_care_record")
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

    private String historyDescription;
    private String prescription;
    private String examRequest;
}
