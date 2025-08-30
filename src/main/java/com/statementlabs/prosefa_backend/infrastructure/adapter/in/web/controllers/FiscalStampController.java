package com.statementlabs.prosefa_backend.infrastructure.adapter.in.web.controllers;

import com.statementlabs.prosefa_backend.application.domain.model.FiscalStamp;
import com.statementlabs.prosefa_backend.application.domain.service.FiscalStampService;
import com.statementlabs.prosefa_backend.infrastructure.dto.FiscalStampRequestDTO;
import com.statementlabs.prosefa_backend.infrastructure.dto.FiscalStampValidationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fiscal-stamps")
@RequiredArgsConstructor
public class FiscalStampController {

    private final FiscalStampService fiscalStampService;

    @PostMapping("/request")
    public ResponseEntity<FiscalStamp> requestFiscalStamp(@RequestBody FiscalStampRequestDTO dto) {
        FiscalStamp newStamp = fiscalStampService.requestFiscalStamp(dto.getCompanyId(), dto.getProduct());
        return new ResponseEntity<>(newStamp, HttpStatus.CREATED);
    }
    @PostMapping("/validate")
    public ResponseEntity<FiscalStamp> validateFiscalStamp(@RequestBody FiscalStampValidationDTO dto) {
        FiscalStamp validatedStamp = fiscalStampService.validateFiscalStamp(dto.getCode());
        return ResponseEntity.ok(validatedStamp);
    }
}