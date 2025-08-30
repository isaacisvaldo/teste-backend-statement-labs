package com.statementlabs.prosefa_backend.application.port.in.usecase;

import org.springframework.data.domain.Page;

import com.statementlabs.prosefa_backend.application.domain.model.AuditLog;
import com.statementlabs.prosefa_backend.infrastructure.dto.AuditLogFilterDTO;



public interface AuditLogUseCase {
    void log(String entity, String action, String user, String details);
     Page<AuditLog> findAllWithFilter(AuditLogFilterDTO filter);
}
