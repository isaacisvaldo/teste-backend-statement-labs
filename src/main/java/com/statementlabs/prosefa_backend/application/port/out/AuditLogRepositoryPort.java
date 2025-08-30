package com.statementlabs.prosefa_backend.application.port.out;

import org.springframework.data.domain.Page;

import com.statementlabs.prosefa_backend.application.domain.model.AuditLog;
import com.statementlabs.prosefa_backend.infrastructure.dto.AuditLogFilterDTO;

public interface AuditLogRepositoryPort {
    AuditLog save(AuditLog auditLog);
     Page<AuditLog> findAllWithFilters(AuditLogFilterDTO filter);
}