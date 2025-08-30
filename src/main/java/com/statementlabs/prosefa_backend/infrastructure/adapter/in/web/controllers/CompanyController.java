package com.statementlabs.prosefa_backend.infrastructure.adapter.in.web.controllers;

import com.statementlabs.prosefa_backend.application.domain.model.Company;
import com.statementlabs.prosefa_backend.infrastructure.dto.CompanyFilterDTO;
import com.statementlabs.prosefa_backend.infrastructure.dto.CompanyRegistrationDTO;
import com.statementlabs.prosefa_backend.infrastructure.dto.CompanyStatusUpdateDTO;
import com.statementlabs.prosefa_backend.infrastructure.response.ApiResponse;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
                newCompany);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> getCompanyById(@PathVariable UUID id) {
        Company company = companyUseCase.findCompanyById(id);

        ApiResponse<Company> apiResponse = ApiResponse.success(
                "Company retrieved successfully.",
                HttpStatus.OK,
                company);
        return ResponseEntity.ok(apiResponse);
    }

@PutMapping("/{id}")
public ResponseEntity<ApiResponse<Company>> updateCompany(
        @PathVariable UUID id,
        @Validated @RequestBody CompanyRegistrationDTO dto) {

    Company updatedCompany = companyUseCase.updateCompany(id, dto);
    return ResponseEntity.ok(
            ApiResponse.success("Company updated successfully.", HttpStatus.OK, updatedCompany)
    );
}
  @PatchMapping("/{id}/status")
public ResponseEntity<ApiResponse<Company>> updateCompanyStatus(
        @PathVariable UUID id,
        @Validated @RequestBody CompanyStatusUpdateDTO dto) {

    Company updatedCompany = companyUseCase.updateCompanyStatus(id, dto.getStatus());
    return ResponseEntity.ok(
            ApiResponse.success("Company status updated successfully.", HttpStatus.OK, updatedCompany)
    );
}
    @GetMapping
    public ResponseEntity<ApiResponse<Page<Company>>> getCompanies(@ParameterObject CompanyFilterDTO filter) {
        Page<Company> companiesPage = companyUseCase.findAllWithFilter(filter);
        ApiResponse<Page<Company>> apiResponse = ApiResponse.success(
                "Companies retrieved successfully.",
                HttpStatus.OK,
                companiesPage);

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(@PathVariable UUID id) {
        companyUseCase.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.success("Company deleted successfully.", HttpStatus.NO_CONTENT, null));
    }

}
