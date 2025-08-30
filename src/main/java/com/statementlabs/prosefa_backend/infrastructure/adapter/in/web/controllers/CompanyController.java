package com.statementlabs.prosefa_backend.infrastructure.adapter.in.web.controllers;

import com.statementlabs.prosefa_backend.application.domain.model.Company;
import com.statementlabs.prosefa_backend.application.domain.model.CompanyStatus;
import com.statementlabs.prosefa_backend.application.domain.model.CompanyType;
import com.statementlabs.prosefa_backend.application.domain.service.CompanyService;
import com.statementlabs.prosefa_backend.infrastructure.dto.CompanyRegistrationDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> registerCompany(@RequestBody CompanyRegistrationDTO dto) {
        Company company = new Company();
        company.setName(dto.getName());
        company.setNif(dto.getNif());
        company.setType(CompanyType.valueOf(dto.getType().toUpperCase()));
        company.setStatus(CompanyStatus.valueOf(dto.getStatus().toUpperCase()));
        company.setRegistrationDate(LocalDateTime.now());
        Company registeredCompany = companyService.registerCompany(company);
        return new ResponseEntity<>(registeredCompany, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable UUID id) {
        Company company = companyService.findCompanyById(id);
        return ResponseEntity.ok(company);
    }
}