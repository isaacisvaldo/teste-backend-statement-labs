package com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.jpa;

import com.statementlabs.prosefa_backend.application.domain.model.Company;
import com.statementlabs.prosefa_backend.application.port.out.CompanyRepositoryPort;
import com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.repository.CompanyRepository;

import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class JpaCompanyRepositoryAdapter implements CompanyRepositoryPort {

    private final CompanyRepository companyRepository;
     public JpaCompanyRepositoryAdapter(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
       
    }
    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> findById(UUID id) {
        return companyRepository.findById(id);
    }

    @Override
    public Optional<Company> findByNif(String nif) {
        return companyRepository.findByNif(nif);
    }

    @Override
    public boolean existsByNif(String nif) {
        return companyRepository.existsByNif(nif);
    }
}