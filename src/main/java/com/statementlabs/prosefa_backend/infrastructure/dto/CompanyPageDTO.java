package com.statementlabs.prosefa_backend.infrastructure.dto;

import com.statementlabs.prosefa_backend.application.domain.model.Company;
import java.util.List;

public class CompanyPageDTO {
    private List<Company> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;

    public CompanyPageDTO(List<Company> content, int pageNumber, int pageSize, long totalElements, int totalPages) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    // Getters e setters
    public List<Company> getContent() { return content; }
    public void setContent(List<Company> content) { this.content = content; }

    public int getPageNumber() { return pageNumber; }
    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public long getTotalElements() { return totalElements; }
    public void setTotalElements(long totalElements) { this.totalElements = totalElements; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
}
