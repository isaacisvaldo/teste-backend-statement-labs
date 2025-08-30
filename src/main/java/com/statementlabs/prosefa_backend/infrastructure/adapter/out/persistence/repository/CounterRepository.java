package com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.statementlabs.prosefa_backend.application.domain.model.Counter;

public interface CounterRepository extends JpaRepository<Counter, String> {
}