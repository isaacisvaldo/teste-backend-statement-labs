package com.statementlabs.prosefa_backend.infrastructure.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class FiscalStampRequestDTO {
    private UUID companyId;
    private String product;
}
