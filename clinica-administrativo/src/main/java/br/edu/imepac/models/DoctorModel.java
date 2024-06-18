package br.edu.imepac.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String crm;
    private String password;

    @OneToMany
    @JoinColumn(name = "doctor_id")
    private List<SpecialtyModel> specialties;

    public DoctorModel(Long id, String name, String password, String crm) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.crm = crm;
    }
}
