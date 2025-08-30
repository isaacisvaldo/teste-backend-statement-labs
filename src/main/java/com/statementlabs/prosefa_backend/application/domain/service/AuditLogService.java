package com.statementlabs.prosefa_backend.application.domain.service;

import com.statementlabs.prosefa_backend.application.domain.model.AuditLog;
import com.statementlabs.prosefa_backend.application.port.in.usecase.AuditLogUseCase;
import com.statementlabs.prosefa_backend.application.port.out.AuditLogRepositoryPort;
import com.statementlabs.prosefa_backend.infrastructure.dto.AuditLogFilterDTO;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class AuditLogService implements AuditLogUseCase {

    private final AuditLogRepositoryPort auditLogRepositoryPort;
    public AuditLogService(AuditLogRepositoryPort auditLogRepositoryPort) {
        this.auditLogRepositoryPort = auditLogRepositoryPort;
        
    }

    @Override
    public void log(String entity, String action, String user, String details) {
        AuditLog auditLog = new AuditLog();
        auditLog.setEntity(entity);
        auditLog.setAction(action);
        auditLog.setAudit_user(user);
        auditLog.setDetails(details);
        auditLog.setDateTime(LocalDateTime.now());
        auditLogRepositoryPort.save(auditLog);
    }

      @Override
    public Page<AuditLog> findAllWithFilter(AuditLogFilterDTO filter) {
        return auditLogRepositoryPort.findAllWithFilters(filter);
    }
}
