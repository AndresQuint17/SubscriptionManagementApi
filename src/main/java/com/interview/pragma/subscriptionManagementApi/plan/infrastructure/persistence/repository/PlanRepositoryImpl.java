package com.interview.pragma.subscriptionManagementApi.plan.infrastructure.persistence.repository;

import com.interview.pragma.subscriptionManagementApi.plan.domain.model.Plan;
import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import com.interview.pragma.subscriptionManagementApi.plan.infrastructure.persistence.mappers.PlanEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlanRepositoryImpl implements IPlanRepository {

    private final IPlanJpaRepository jpaRepository;
    private final PlanEntityMapper mapper;

    @Override
    public Plan save(Plan plan) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(plan)));
    }

    @Override
    public Optional<Plan> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Plan> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
