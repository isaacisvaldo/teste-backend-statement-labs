package com.statementlabs.prosefa_backend.infrastructure.adapter.in.web.controllers;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.statementlabs.prosefa_backend.application.domain.model.AuditLog;
import com.statementlabs.prosefa_backend.application.port.in.usecase.AuditLogUseCase;
import com.statementlabs.prosefa_backend.infrastructure.dto.AuditLogFilterDTO;
import com.statementlabs.prosefa_backend.infrastructure.response.ApiResponse;


@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    private final AuditLogUseCase auditLogUseCase;

    public AuditLogController(AuditLogUseCase auditLogUseCase) {
        this.auditLogUseCase = auditLogUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<AuditLog>>> getAuditLogs(@ParameterObject AuditLogFilterDTO filter) {
        Page<AuditLog> auditLogs = auditLogUseCase.findAllWithFilter(filter);

        ApiResponse<Page<AuditLog>> apiResponse = ApiResponse.success(
                "Audit logs retrieved successfully.",
                HttpStatus.OK,
                auditLogs
        );

        return ResponseEntity.ok(apiResponse);
    }
}
