package br.edu.imepac.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rgNumber;
    private String cpfNumber;
    private String adress;
    private String city;
    private String state;
    private String phoneNumber;
    private boolean hasHealthInsurance;
    private String healthInsurance;
}
