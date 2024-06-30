package br.edu.imepac.dtos.specialty;

import br.edu.imepac.models.administrativo.SpecialtyModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SpecialtyDto {

    private Long id;
    private String description;

    public SpecialtyDto(SpecialtyModel model){
        this.id = model.getId();
        this.description = model.getDescription();
    }
}
