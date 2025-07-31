package com.interview.pragma.subscriptionManagementApi.plan.infrastructure.persistence.repository;

import com.interview.pragma.subscriptionManagementApi.plan.infrastructure.persistence.entities.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPlanJpaRepository extends JpaRepository<PlanEntity, Long> {
    boolean existsByName(String name);
    Optional<PlanEntity> findByName(String name);
}
