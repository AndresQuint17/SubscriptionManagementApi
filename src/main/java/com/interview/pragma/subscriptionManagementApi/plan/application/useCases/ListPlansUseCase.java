package com.interview.pragma.subscriptionManagementApi.plan.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanResponseDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.mappers.PlanDtoMapper;
import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListPlansUseCase {

    private final IPlanRepository planRepository;
    private final PlanDtoMapper mapper;

    public List<PlanResponseDto> execute() {
        return planRepository.findAll().stream()
                .map(mapper::toResponseDto)
                .toList();
    }
}
