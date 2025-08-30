package com.statementlabs.prosefa_backend.application.port.out;

import java.util.Optional;
import java.util.UUID;

import com.statementlabs.prosefa_backend.application.domain.model.Company;

public interface CompanyRepositoryPort {
    Company save(Company company);
    Optional<Company> findById(UUID id);
    Optional<Company> findByNif(String nif);
    boolean existsByNif(String nif);
}