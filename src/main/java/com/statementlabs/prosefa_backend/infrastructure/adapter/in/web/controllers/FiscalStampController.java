package com.statementlabs.prosefa_backend.infrastructure.adapter.in.web.controllers;

import com.statementlabs.prosefa_backend.application.domain.model.FiscalStamp;
import com.statementlabs.prosefa_backend.application.domain.model.FiscalStampState;
import com.statementlabs.prosefa_backend.infrastructure.dto.FiscalStampRequestDTO;
import com.statementlabs.prosefa_backend.infrastructure.dto.FiscalStampValidationDTO;
import com.statementlabs.prosefa_backend.infrastructure.response.ApiResponse;
import com.statementlabs.prosefa_backend.infrastructure.exception.ResourceNotFoundException;
import com.statementlabs.prosefa_backend.application.port.in.usecase.FiscalStampUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/fiscal-stamps")
public class FiscalStampController {
    private final FiscalStampUseCase fiscalStampUseCase;

    public FiscalStampController(FiscalStampUseCase fiscalStampUseCase) {
        this.fiscalStampUseCase = fiscalStampUseCase;
    }

    @PostMapping("/request")
    public ResponseEntity<ApiResponse<FiscalStamp>> requestFiscalStamp(
            @Validated @RequestBody FiscalStampRequestDTO dto) {
        try {
            FiscalStamp newStamp = fiscalStampUseCase.requestFiscalStamp(dto.getCompanyId(), dto.getProduct());
            ApiResponse<FiscalStamp> apiResponse = ApiResponse.success(
                    "Fiscal stamp requested successfully.",
                    HttpStatus.CREATED,
                    newStamp
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        } catch (IllegalArgumentException | ResourceNotFoundException e) {
            ApiResponse<FiscalStamp> apiResponse = ApiResponse.error(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<ApiResponse<FiscalStamp>> validateFiscalStamp(
            @Validated @RequestBody FiscalStampValidationDTO dto) {
        try {
            FiscalStamp validatedStamp = fiscalStampUseCase.validateFiscalStamp(dto.getCode());
            ApiResponse<FiscalStamp> apiResponse = ApiResponse.success(
                    "Fiscal stamp validated successfully.",
                    HttpStatus.OK,
                    validatedStamp
            );
            return ResponseEntity.ok(apiResponse);
        } catch (IllegalArgumentException | ResourceNotFoundException e) {
            ApiResponse<FiscalStamp> apiResponse = ApiResponse.error(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

 
    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse<FiscalStamp>> getFiscalStampByCode(@PathVariable String code) {
        return fiscalStampUseCase.findByCode(code)
                .map(stamp -> ResponseEntity.ok(
                        ApiResponse.success("Fiscal stamp found.", HttpStatus.OK, stamp)))
                .orElseThrow(() -> new ResourceNotFoundException("Fiscal stamp not found with code: " + code));
    }


    @GetMapping("/company/{companyId}")
    public ResponseEntity<ApiResponse<List<FiscalStamp>>> getFiscalStampsByCompanyAndState(
            @PathVariable UUID companyId,
            @RequestParam FiscalStampState state) {
        List<FiscalStamp> stamps = fiscalStampUseCase.findByCompanyIdAndState(companyId, state);
        ApiResponse<List<FiscalStamp>> apiResponse = ApiResponse.success(
                "Fiscal stamps retrieved successfully.",
                HttpStatus.OK,
                stamps
        );
        return ResponseEntity.ok(apiResponse);
    }
}
