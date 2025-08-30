package com.statementlabs.prosefa_backend.infrastructure.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CompanyRegistrationDTO {
    private String name;
    private String nif;
    private String type; 
    private String status;
    private LocalDateTime registrationDate;
}