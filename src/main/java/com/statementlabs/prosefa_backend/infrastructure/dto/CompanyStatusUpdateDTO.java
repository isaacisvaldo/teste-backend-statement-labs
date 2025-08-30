package com.statementlabs.prosefa_backend.infrastructure.dto;



import com.statementlabs.prosefa_backend.application.domain.model.CompanyStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class CompanyStatusUpdateDTO {

    @NotNull(message = "Status cannot be null")
    @Schema(description = "New status of the company", 
            example = "ACTIVE", 
            allowableValues = {"ACTIVE", "SUSPENDED", "BLOCKED"})
    private CompanyStatus status;

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }
}
