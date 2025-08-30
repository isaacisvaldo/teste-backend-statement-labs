package com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.repository;

import com.statementlabs.prosefa_backend.application.domain.model.FiscalStamp;
import com.statementlabs.prosefa_backend.application.domain.model.FiscalStampState;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FiscalStampRepository extends JpaRepository<FiscalStamp, UUID> {
    
    Optional<FiscalStamp> findByCode(String code);
    List<FiscalStamp> findByCompanyIdAndState(UUID companyId, FiscalStampState state);
}