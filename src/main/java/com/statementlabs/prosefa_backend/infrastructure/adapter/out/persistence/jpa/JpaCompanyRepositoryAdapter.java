package com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.jpa;

import com.statementlabs.prosefa_backend.application.domain.model.Company;
import com.statementlabs.prosefa_backend.application.port.out.CompanyRepositoryPort;
import com.statementlabs.prosefa_backend.infrastructure.adapter.out.persistence.repository.CompanyRepository;
import com.statementlabs.prosefa_backend.infrastructure.dto.CompanyFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Sort;



@Component
public class JpaCompanyRepositoryAdapter implements CompanyRepositoryPort {

    private final CompanyRepository companyRepository;

    public JpaCompanyRepositoryAdapter(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> findById(UUID id) {
        return companyRepository.findById(id);
    }

    @Override
    public Optional<Company> findByNif(String nif) {
        return companyRepository.findByNif(nif);
    }

    @Override
    public boolean existsByNif(String nif) {
        return companyRepository.existsByNif(nif);
    }

@Override
public Page<Company> findAllWithFilters(CompanyFilterDTO filter) {
    Specification<Company> specification = (root, query, criteriaBuilder) -> {
        jakarta.persistence.criteria.Predicate predicate = criteriaBuilder.conjunction();

        // Filtro por search (name ou description)
        if (filter.getSearch() != null && !filter.getSearch().isEmpty()) {
            String likePattern = "%" + filter.getSearch() + "%";
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get("name"), likePattern),
                            criteriaBuilder.like(root.get("nif"), likePattern)
                    ));
        }

        // Filtro por tipo
    if (filter.getType() != null) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(root.get("type"), filter.getType()));
        }

        // Filtro por status
        if (filter.getStatus() != null) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(root.get("status"), filter.getStatus()));
        }

        return predicate;
    };

    Pageable pageable = PageRequest.of(
            filter.getPage(),
            filter.getSize(),
            filter.getSortDirection() == Sort.Direction.DESC ?
                    Sort.by(filter.getSortBy()).descending() :
                    Sort.by(filter.getSortBy()).ascending()
    );

    return companyRepository.findAll(specification, pageable);
}

   @Override
    public void delete(Company company) {
        companyRepository.delete(company);
    }

    @Override
    public void deleteById(UUID id) {
        companyRepository.deleteById(id);
    }

}

