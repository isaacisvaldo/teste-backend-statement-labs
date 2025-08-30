package com.statementlabs.prosefa_backend.application.domain.service;

import com.statementlabs.prosefa_backend.application.domain.model.Company;

import com.statementlabs.prosefa_backend.application.port.in.usecase.CompanyUseCase;
import com.statementlabs.prosefa_backend.application.port.out.CompanyRepositoryPort;
import com.statementlabs.prosefa_backend.infrastructure.dto.CompanyRegistrationDTO;
import com.statementlabs.prosefa_backend.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CompanyService implements CompanyUseCase {

    private final CompanyRepositoryPort companyRepositoryPort;

    public CompanyService(CompanyRepositoryPort companyRepositoryPort) {
        this.companyRepositoryPort = companyRepositoryPort;
    }

    @Override
    public Company registerCompany(CompanyRegistrationDTO dto) {
        if (companyRepositoryPort.existsByNif(dto.getNif())) {
            throw new IllegalArgumentException("Company with this NIF already exists.");
        }

        // Converter DTO -> Domain
        Company company = new Company();
        company.setName(dto.getName());
        company.setNif(dto.getNif());
        company.setType(dto.getType());
        company.setStatus(dto.getStatus());
        company.setRegistrationDate(dto.getRegistrationDate());

        return companyRepositoryPort.save(company);
    }

    @Override
    public Company findCompanyById(UUID id) {
        return companyRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + id));
    }

    @Override
    public Company updateCompanyStatus(UUID id, CompanyRegistrationDTO newCompanyData) {
        Company company = findCompanyById(id);
        company.setStatus(newCompanyData.getStatus());
        return companyRepositoryPort.save(company);
    }
}
