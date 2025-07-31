package com.interview.pragma.subscriptionManagementApi.plan.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanRequestDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanResponseDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.mappers.PlanDtoMapper;
import com.interview.pragma.subscriptionManagementApi.plan.domain.model.Plan;
import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdatePlanUseCase {

    private final IPlanRepository repository;
    private final PlanDtoMapper mapper;

    public PlanResponseDto execute(Long id, PlanRequestDto dto) {
        Plan existingPlan = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found with ID: " + id));

        // Validar que el nombre no esté en uso por otro plan
        boolean nameExists = repository.existsByName(dto.getName());
        if (nameExists && !existingPlan.getName().equals(dto.getName())) {
            throw new IllegalArgumentException("Plan name already exists");
        }

        // Crear nueva instancia con los datos actualizados
        Plan updatedPlan = new Plan(
                existingPlan.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getBillingPeriod(),
                existingPlan.getSubscriptionIds().orElse(null)
        );

        return mapper.toResponseDto(repository.save(updatedPlan));
    }
}
