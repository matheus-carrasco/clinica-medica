package br.edu.imepac.models.administrativo;

import br.edu.imepac.models.agendamento.ScheduleModel;
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

    @ManyToMany
    @JoinTable(
            name = "doctor_specialty",  // nome da tabela de junção
            joinColumns = @JoinColumn(name = "doctor_id"),  // coluna que referencia DoctorModel
            inverseJoinColumns = @JoinColumn(name = "specialty_id")  // coluna que referencia SpecialtyModel
    )
    private List<SpecialtyModel> specialties;

    @OneToMany(mappedBy = "doctor")
    private List<ScheduleModel> schedules;

    public DoctorModel(Long id, String name, String password, String crm) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.crm = crm;
    }
}
