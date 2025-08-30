package com.statementlabs.prosefa_backend.application.port.in.usecase;

import com.statementlabs.prosefa_backend.application.domain.model.FiscalStamp;

import java.util.UUID;

public interface FiscalStampUseCase {

    FiscalStamp requestFiscalStamp(UUID companyId, String product);

    FiscalStamp validateFiscalStamp(String code);
}
