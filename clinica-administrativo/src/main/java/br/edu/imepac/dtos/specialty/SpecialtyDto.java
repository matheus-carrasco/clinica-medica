package br.edu.imepac.dtos.specialty;

import br.edu.imepac.models.SpecialtyModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyDto {

    private Long id;
    private String description;

    public SpecialtyDto (SpecialtyModel model){
        this.id = model.getId();
        this.description = model.getDescription();
    }
}
