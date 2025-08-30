package com.statementlabs.prosefa_backend.application.port.in.usecase;

public interface AuditLogUseCase {
    void log(String entity, String action, String user, String details);
}
