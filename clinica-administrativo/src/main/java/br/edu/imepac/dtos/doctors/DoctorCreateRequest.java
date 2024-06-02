package br.edu.imepac.dtos.doctors;

import lombok.Data;

@Data
public class DoctorCreateRequest {
    private String name;
    private String crm;
    private String password;

}
