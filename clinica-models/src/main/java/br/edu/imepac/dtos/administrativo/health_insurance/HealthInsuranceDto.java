package br.edu.imepac.dtos.health_insurance;

import br.edu.imepac.models.administrativo.HealthInsuranceModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthInsuranceDto {
    private Long id;
    private String name;
    private String cnpj;
    private String phone;

    public HealthInsuranceDto(HealthInsuranceModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.cnpj = model.getCnpj();
        this.phone = model.getPhone();
    }
}

