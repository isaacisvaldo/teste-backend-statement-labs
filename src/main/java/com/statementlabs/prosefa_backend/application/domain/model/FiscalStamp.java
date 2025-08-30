package com.statementlabs.prosefa_backend.application.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class FiscalStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String product;

    private LocalDateTime issueDate;

    @Enumerated(EnumType.STRING)
    private FiscalStampState state;

    // Getters e Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public FiscalStampState getState() {
        return state;
    }

    public void setState(FiscalStampState state) {
        this.state = state;
    }
}
