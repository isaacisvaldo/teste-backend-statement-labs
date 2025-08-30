package com.statementlabs.prosefa_backend.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.statementlabs.prosefa_backend.application.domain.model.FiscalStamp;
import com.statementlabs.prosefa_backend.application.domain.model.FiscalStampState;

public interface FiscalStampRepositoryPort {
    FiscalStamp save(FiscalStamp fiscalStamp);
    Optional<FiscalStamp> findByCode(String code);
    List<FiscalStamp> findByCompanyIdAndState(UUID companyId, FiscalStampState state);
}