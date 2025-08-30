package com.statementlabs.prosefa_backend.infrastructure.dto;

public class FiscalStampValidationDTO {
    private String code;

    public FiscalStampValidationDTO() {
    }

    public FiscalStampValidationDTO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
