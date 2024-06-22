package br.edu.imepac.dtos.employees;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class EmployeeCreateRequest {

    private String name;
    private String idNumber;
    private String CpfNumber;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String workPermitNumber;
    private String pisNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
}
