package br.edu.imepac.dtos.administrativo.health_insurance;

import lombok.Data;

@Data
public class HealthInsuranceCreateRequest {
    private String name;
    private String cnpj;
    private String phone;
}
