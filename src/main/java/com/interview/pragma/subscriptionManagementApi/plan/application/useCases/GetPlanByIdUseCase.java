package com.interview.pragma.subscriptionManagementApi.plan.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanResponseDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.mappers.PlanDtoMapper;
import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPlanByIdUseCase {

    private final IPlanRepository planRepository;
    private final PlanDtoMapper mapper;

    public PlanResponseDto execute(Long id) {
        return planRepository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found with id: " + id));
    }
}
