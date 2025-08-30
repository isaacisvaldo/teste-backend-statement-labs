package com.statementlabs.prosefa_backend.infrastructure.adapter.in.web.controllers;
import com.statementlabs.prosefa_backend.application.domain.model.FiscalStamp;
import com.statementlabs.prosefa_backend.infrastructure.dto.FiscalStampRequestDTO;
import com.statementlabs.prosefa_backend.infrastructure.dto.FiscalStampValidationDTO;
import com.statementlabs.prosefa_backend.infrastructure.response.ApiResponse;
import com.statementlabs.prosefa_backend.infrastructure.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.statementlabs.prosefa_backend.application.port.in.usecase.FiscalStampUseCase;

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
}
