package com.statementlabs.prosefa_backend.application.port.out;

import com.statementlabs.prosefa_backend.application.domain.model.AuditLog;

public interface AuditLogRepositoryPort {
    AuditLog save(AuditLog auditLog);
}