package com.statementlabs.prosefa_backend.infrastructure.dto;

import java.util.UUID;

public class FiscalStampRequestDTO {
    private UUID companyId;
    private String product;

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(UUID companyId) {
        this.companyId = companyId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
