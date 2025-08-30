package com.statementlabs.prosefa_backend.application.port.in.usecase;

import com.statementlabs.prosefa_backend.application.domain.model.FiscalStamp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.statementlabs.prosefa_backend.application.domain.model.FiscalStampState;

public interface FiscalStampUseCase {

    FiscalStamp requestFiscalStamp(UUID companyId, String product);

    FiscalStamp validateFiscalStamp(String code);

    Optional<FiscalStamp> findByCode(String code);  

    List<FiscalStamp> findByCompanyIdAndState(UUID companyId, FiscalStampState state); 
}
