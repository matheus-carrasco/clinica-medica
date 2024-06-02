package br.edu.imepac.dtos.health_insurance;

import lombok.Data;

@Data
public class HealthInsuranceCreateRequest {

    private String name;
    private String cnpj;
    private String telefone;
}
