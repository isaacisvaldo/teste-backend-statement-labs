package com.statementlabs.prosefa_backend.application.port.in.usecase;

import com.statementlabs.prosefa_backend.application.domain.model.Company;
import com.statementlabs.prosefa_backend.infrastructure.dto.CompanyRegistrationDTO;
import java.util.UUID;

public interface CompanyUseCase {

    Company registerCompany(CompanyRegistrationDTO company);

    Company findCompanyById(UUID id);

    Company updateCompanyStatus(UUID id, CompanyRegistrationDTO newCompanyData);
}
