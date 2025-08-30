package com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.repository;


import com.statementlabs.prosefa_backend.application.domain.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {}
