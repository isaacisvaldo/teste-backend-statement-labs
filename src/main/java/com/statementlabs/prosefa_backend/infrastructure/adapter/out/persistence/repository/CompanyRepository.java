package com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;
import java.util.UUID;
import com.statementlabs.prosefa_backend.application.domain.model.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID>, JpaSpecificationExecutor<Company> {
    Optional<Company> findByNif(String nif);
    boolean existsByNif(String nif);
}
