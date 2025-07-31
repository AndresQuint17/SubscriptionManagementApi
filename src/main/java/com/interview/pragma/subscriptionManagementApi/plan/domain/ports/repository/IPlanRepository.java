package com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository;

import com.interview.pragma.subscriptionManagementApi.plan.domain.model.Plan;

import java.util.List;
import java.util.Optional;

public interface IPlanRepository {
    Plan save(Plan plan);
    Optional<Plan> findById(Long id);
    List<Plan> findAll();
    boolean existsByName(String name);
    void deleteById(Long id);
}
