package com.interview.pragma.subscriptionManagementApi.plan.application.mappers;

import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanRequestDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanResponseDto;
import com.interview.pragma.subscriptionManagementApi.plan.domain.model.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanDtoMapper {

    public Plan toDomain(PlanRequestDto dto) {
        return new Plan(
                null,
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getBillingPeriod(),
                null
        );
    }

    public PlanResponseDto toResponseDto(Plan plan) {
        return new PlanResponseDto(
                plan.getId(),
                plan.getName(),
                plan.getDescription(),
                plan.getPrice(),
                plan.getBillingPeriod(),
                plan.getSubscriptionIds().orElse(null)
        );
    }
}
