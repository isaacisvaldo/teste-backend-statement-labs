package com.statementlabs.prosefa_backend.infrastructure.dto;

import lombok.Data;

import org.springframework.data.domain.Sort;

import com.statementlabs.prosefa_backend.application.domain.model.CompanyStatus;
import com.statementlabs.prosefa_backend.application.domain.model.CompanyType;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class CompanyFilterDTO {

    @Schema(description = "Search term to filter by company name or nif.", example = "Tech")
    private String search;

   
    private CompanyStatus status;

    private CompanyType type; 

    // Pagination and Sorting fields
    private int page = 0;
    private int size = 10;
    private String sortBy = "id";
    private Sort.Direction sortDirection = Sort.Direction.ASC;

    // ----- Getters and Setters -----

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public CompanyStatus getStatus() {
        return status;
    }

    public void setStatus(CompanyStatus status) {
        this.status = status;
    }

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
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
