package com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.jpa;

import com.statementlabs.prosefa_backend.application.domain.model.FiscalStamp;
import com.statementlabs.prosefa_backend.application.domain.model.FiscalStampState;
import com.statementlabs.prosefa_backend.application.port.out.FiscalStampRepositoryPort;
import com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.repository.FiscalStampRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class JpaFiscalStampRepositoryAdapter implements FiscalStampRepositoryPort {

    private final FiscalStampRepository fiscalStampRepository;

     public JpaFiscalStampRepositoryAdapter(FiscalStampRepository fiscalStampRepository) {
        this.fiscalStampRepository = fiscalStampRepository;
       
    }


    @Override
    public FiscalStamp save(FiscalStamp fiscalStamp) {
        return fiscalStampRepository.save(fiscalStamp);
    }

    @Override
    public Optional<FiscalStamp> findByCode(String code) {
        return fiscalStampRepository.findByCode(code);
    }

    @Override
    public List<FiscalStamp> findByCompanyIdAndState(UUID companyId, FiscalStampState state) {
        return fiscalStampRepository.findByCompanyIdAndState(companyId, state);
    }
}