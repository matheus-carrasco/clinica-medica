package br.edu.imepac.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "specialties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyModel  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "specialty")
    private List<DoctorModel> doctors;
/*
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = true)
    private DoctorModel doctors;
*/
    public SpecialtyModel(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
