package com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.repository;
import com.statementlabs.prosefa_backend.application.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
      Optional<Company> findByNif(String nif);
    boolean existsByNif(String nif);
    
}