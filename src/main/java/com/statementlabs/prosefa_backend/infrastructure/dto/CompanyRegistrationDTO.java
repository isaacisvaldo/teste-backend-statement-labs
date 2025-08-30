package com.statementlabs.prosefa_backend.infrastructure.dto;

import java.time.LocalDateTime;

import com.statementlabs.prosefa_backend.application.domain.model.CompanyStatus;
import com.statementlabs.prosefa_backend.application.domain.model.CompanyType;

public class CompanyRegistrationDTO {
    private String name;
    private String nif;
    private CompanyType type; 
    private CompanyStatus status;
    private LocalDateTime registrationDate;

    // Getters e Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
