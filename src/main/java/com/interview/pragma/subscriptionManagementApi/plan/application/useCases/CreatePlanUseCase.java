package com.interview.pragma.subscriptionManagementApi.plan.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanRequestDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanResponseDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.mappers.PlanDtoMapper;
import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePlanUseCase {

    private final IPlanRepository planRepository;
    private final PlanDtoMapper mapper;

    public PlanResponseDto execute(PlanRequestDto dto) {
        if (planRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Plan name already exists.");
        }

        var plan = mapper.toDomain(dto);
        var saved = planRepository.save(plan);
        return mapper.toResponseDto(saved);
    }
}
