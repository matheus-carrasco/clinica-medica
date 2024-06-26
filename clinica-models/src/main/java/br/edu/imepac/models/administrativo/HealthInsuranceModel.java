package br.edu.imepac.models.administrativo;

import br.edu.imepac.models.agendamento.PatientModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "health_insurances")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsuranceModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cnpj;
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "healthInsurance", cascade = CascadeType.ALL)
    private List<PatientModel> patients;
}

