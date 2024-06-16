package br.edu.imepac.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient_care_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientCareRecordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String historyDescription;
    private String prescription;
    private String examRequest;
}
