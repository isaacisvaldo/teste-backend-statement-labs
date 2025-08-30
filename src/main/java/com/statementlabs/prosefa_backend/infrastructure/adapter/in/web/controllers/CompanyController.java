package com.statementlabs.prosefa_backend.infrastructure.adapter.in.web.controllers;

import com.statementlabs.prosefa_backend.application.domain.model.Company;
import com.statementlabs.prosefa_backend.infrastructure.dto.CompanyRegistrationDTO;

import com.statementlabs.prosefa_backend.infrastructure.response.ApiResponse;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import com.statementlabs.prosefa_backend.application.port.in.usecase.CompanyUseCase;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyUseCase companyUseCase;

    public CompanyController(CompanyUseCase companyUseCase) {
        this.companyUseCase = companyUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Company>> registerCompany(
            @Validated @RequestBody CompanyRegistrationDTO dto) {
        Company newCompany = companyUseCase.registerCompany(dto);
        ApiResponse<Company> apiResponse = ApiResponse.success(
                "Company registered successfully.",
                HttpStatus.CREATED,
                newCompany
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
 /* 
    @GetMapping
    public ResponseEntity<ApiResponse<Page<Company>>> getCompanies() {
        Page<Company> companiesPage = companyUseCase.findCompanies();
        ApiResponse<Page<Company>> apiResponse = ApiResponse.success(
                "Companies retrieved successfully.",
                HttpStatus.OK,
                companiesPage
        );
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> getCompanyById(@PathVariable UUID id) {
        Optional<Company> company = companyUseCase.findCompanyById(id);

        if (company.isPresent()) {
            ApiResponse<Company> apiResponse = ApiResponse.success(
                    "Company retrieved successfully.",
                    HttpStatus.OK,
                    company.get()
            );
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse<Company> apiResponse = ApiResponse.error(
                    "Company not found.",
                    HttpStatus.NOT_FOUND
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> updateCompany(
            @PathVariable UUID id,
            @Validated @RequestBody CompanyRegistrationDTO dto) {
        try {
            Company updatedCompany = companyUseCase.updateCompany(id, dto);
            ApiResponse<Company> apiResponse = ApiResponse.success(
                    "Company updated successfully.",
                    HttpStatus.OK,
                    updatedCompany
            );
            return ResponseEntity.ok(apiResponse);
        } catch (IllegalArgumentException e) {
            ApiResponse<Company> apiResponse = ApiResponse.error(
                    "Company not found.",
                    HttpStatus.NOT_FOUND
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(@PathVariable UUID id) {
        try {
            companyUseCase.deleteCompany(id);
            ApiResponse<Void> apiResponse = ApiResponse.success(
                    "Company deleted successfully.",
                    HttpStatus.NO_CONTENT,
                    null
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            ApiResponse<Void> apiResponse = ApiResponse.error(
                    "Company not found.",
                    HttpStatus.NOT_FOUND
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
        */
}
