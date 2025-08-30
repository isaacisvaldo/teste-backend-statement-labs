package com.statementlabs.prosefa_backend.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

@Schema(description = "Filtro e paginação para listar Audit Logs")
public class AuditLogFilterDTO {

    @Schema(description = "Nome da entidade auditada", example = "FiscalStamp")
    private String entity;

    @Schema(description = "Tipo da ação executada", example = "VALIDATION_PERFORMED")
    private String action;

    @Schema(description = "Usuário responsável pelo log", example = "system")
    private String auditUser;

    @Schema(description = "Data inicial para filtro", example = "2025-01-01T00:00:00")
    private LocalDateTime startDate;

    @Schema(description = "Data final para filtro", example = "2025-12-31T23:59:59")
    private LocalDateTime endDate;


    private int page = 0;
    private int size = 10;
    private String sortBy = "dateTime";
    private Sort.Direction sortDirection = Sort.Direction.DESC;

  

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
    }
}
