package com.statementlabs.prosefa_backend.application.port.in.usecase;

import com.statementlabs.prosefa_backend.application.domain.model.Company;
import com.statementlabs.prosefa_backend.application.domain.model.CompanyStatus;
import com.statementlabs.prosefa_backend.infrastructure.dto.CompanyFilterDTO;

import com.statementlabs.prosefa_backend.infrastructure.dto.CompanyRegistrationDTO;

import java.util.UUID;

import org.springframework.data.domain.Page;

public interface CompanyUseCase {

    Company registerCompany(CompanyRegistrationDTO company);

    Company findCompanyById(UUID id); 

    Company updateCompanyStatus(UUID id, CompanyStatus newStatu);

    Company updateCompany(UUID id, CompanyRegistrationDTO updatedCompanyData); 
     Page<Company> findAllWithFilter(CompanyFilterDTO filter);
     void deleteCompany(UUID id);
}