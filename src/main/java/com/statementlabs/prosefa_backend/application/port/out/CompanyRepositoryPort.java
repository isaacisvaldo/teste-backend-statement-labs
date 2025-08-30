package com.statementlabs.prosefa_backend.application.port.out;

import com.statementlabs.prosefa_backend.application.domain.model.Company;
import com.statementlabs.prosefa_backend.infrastructure.dto.CompanyFilterDTO;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;

public interface CompanyRepositoryPort {
    Company save(Company company);
    Optional<Company> findById(UUID id);
    Optional<Company> findByNif(String nif);
    boolean existsByNif(String nif);
    Page<Company> findAllWithFilters(CompanyFilterDTO filter);
     void delete(Company company);        
    void deleteById(UUID id); 
}
