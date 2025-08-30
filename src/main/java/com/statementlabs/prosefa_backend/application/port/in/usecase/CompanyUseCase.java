package com.statementlabs.prosefa_backend.application.port.in.usecase;

import com.statementlabs.prosefa_backend.application.domain.model.Company;

import java.util.UUID;

public interface CompanyUseCase {

    Company registerCompany(Company company);

    Company findCompanyById(UUID id);

    Company updateCompanyStatus(UUID id, Company newCompanyData);
}
