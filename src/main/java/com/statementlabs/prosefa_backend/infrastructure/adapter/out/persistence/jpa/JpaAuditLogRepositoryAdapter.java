package com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.jpa;

import com.statementlabs.prosefa_backend.application.domain.model.AuditLog;
import com.statementlabs.prosefa_backend.application.port.out.AuditLogRepositoryPort;
import com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.repository.AuditLogRepository;
import com.statementlabs.prosefa_backend.infrastructure.dto.AuditLogFilterDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
@Component
public class JpaAuditLogRepositoryAdapter implements AuditLogRepositoryPort {

    private final AuditLogRepository auditLogRepository;

    public JpaAuditLogRepositoryAdapter(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;

    }

    @Override
    public AuditLog save(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    @Override
    public Page<AuditLog> findAllWithFilters(AuditLogFilterDTO filter) {
        Specification<AuditLog> specification = (root, query, criteriaBuilder) -> {
            jakarta.persistence.criteria.Predicate predicate = criteriaBuilder.conjunction();

            // Filtro por entidade
            if (filter.getEntity() != null && !filter.getEntity().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("entity"), "%" + filter.getEntity() + "%"));
            }

            // Filtro por ação
            if (filter.getAction() != null && !filter.getAction().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("action"), filter.getAction()));
            }

            // Filtro por utilizador
            if (filter.getAuditUser() != null && !filter.getAuditUser().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(root.get("audit_user"), "%" + filter.getAuditUser() + "%"));
            }

            // Filtro por intervalo de datas
            if (filter.getStartDate() != null && filter.getEndDate() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.between(root.get("dateTime"), filter.getStartDate(), filter.getEndDate()));
            } else if (filter.getStartDate() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.greaterThanOrEqualTo(root.get("dateTime"), filter.getStartDate()));
            } else if (filter.getEndDate() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.lessThanOrEqualTo(root.get("dateTime"), filter.getEndDate()));
            }

            return predicate;
        };

        Pageable pageable = PageRequest.of(
                filter.getPage(),
                filter.getSize(),
                filter.getSortDirection() == Sort.Direction.DESC ? Sort.by(filter.getSortBy()).descending()
                        : Sort.by(filter.getSortBy()).ascending());

        return auditLogRepository.findAll(specification, pageable);
    }

}