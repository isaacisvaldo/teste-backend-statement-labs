package com.statementlabs.prosefa_backend.application.domain.service;

import com.statementlabs.prosefa_backend.application.domain.model.Company;
import com.statementlabs.prosefa_backend.application.domain.model.CompanyStatus;
import com.statementlabs.prosefa_backend.application.domain.model.FiscalStamp;
import com.statementlabs.prosefa_backend.application.domain.model.FiscalStampState;
import com.statementlabs.prosefa_backend.application.port.in.usecase.FiscalStampUseCase; // <-- corrigido
import com.statementlabs.prosefa_backend.application.port.out.CompanyRepositoryPort;
import com.statementlabs.prosefa_backend.application.port.out.FiscalStampRepositoryPort;
import com.statementlabs.prosefa_backend.application.port.out.GenerateCodeSequence;
import com.statementlabs.prosefa_backend.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FiscalStampService implements FiscalStampUseCase {
    private final FiscalStampRepositoryPort fiscalStampRepositoryPort;
    private final CompanyRepositoryPort companyRepositoryPort;
    private final AuditLogService auditLogService;
    private final GenerateCodeSequence generateCodeSequence;

    public FiscalStampService(
            FiscalStampRepositoryPort fiscalStampRepositoryPort,
            CompanyRepositoryPort companyRepositoryPort,
            AuditLogService auditLogService,
            GenerateCodeSequence generateCodeSequence
    ) {
        this.companyRepositoryPort = companyRepositoryPort;
        this.fiscalStampRepositoryPort = fiscalStampRepositoryPort;
        this.auditLogService = auditLogService;
        this.generateCodeSequence = generateCodeSequence;
    }

    @Override
    public FiscalStamp requestFiscalStamp(UUID companyId, String product) {
        Company company = companyRepositoryPort.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found."));
        if (company.getStatus() != CompanyStatus.ACTIVE) {
            throw new IllegalArgumentException("Company is not active and cannot request stamps.");
        }
        String newCode = generateCodeSequence.generateNewCodeSequence("PROSEFA");
        FiscalStamp fiscalStamp = new FiscalStamp();
        fiscalStamp.setCode(newCode);
        fiscalStamp.setCompany(company);
        fiscalStamp.setProduct(product);
        fiscalStamp.setIssueDate(LocalDateTime.now());
        fiscalStamp.setState(FiscalStampState.ISSUED);
        FiscalStamp savedStamp = fiscalStampRepositoryPort.save(fiscalStamp);
        auditLogService.log("FiscalStamp", "REQUEST_ISSUED", "system", "New stamp issued with code: " + newCode);

        return savedStamp;
    }

    @Override
    public FiscalStamp validateFiscalStamp(String code) {
        FiscalStamp fiscalStamp = fiscalStampRepositoryPort.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Fiscal stamp not found."));

        if (fiscalStamp.getState() == FiscalStampState.VALIDATED) {
            throw new IllegalArgumentException("Fiscal stamp already validated.");
        }

        fiscalStamp.setState(FiscalStampState.VALIDATED);
        FiscalStamp savedStamp = fiscalStampRepositoryPort.save(fiscalStamp);

        auditLogService.log("FiscalStamp", "VALIDATION_PERFORMED", "user", "Stamp validated: " + code);

        return savedStamp;
    }
}
