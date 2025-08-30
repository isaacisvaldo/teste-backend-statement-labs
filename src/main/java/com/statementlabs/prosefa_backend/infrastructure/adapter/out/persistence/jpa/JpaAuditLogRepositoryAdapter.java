package com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.jpa;


import com.statementlabs.prosefa_backend.application.domain.model.AuditLog;
import com.statementlabs.prosefa_backend.application.port.out.AuditLogRepositoryPort;
import com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.repository.AuditLogRepository;

import org.springframework.stereotype.Component;

@Component
public class JpaAuditLogRepositoryAdapter implements AuditLogRepositoryPort {
    
    private final AuditLogRepository auditLogRepository;
    
 public JpaAuditLogRepositoryAdapter(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
       
    }

    @Override
    public AuditLog save(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }
}