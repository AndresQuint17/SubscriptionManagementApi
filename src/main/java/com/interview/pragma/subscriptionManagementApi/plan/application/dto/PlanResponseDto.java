package com.interview.pragma.subscriptionManagementApi.plan.application.dto;

import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class PlanResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private EBillingPeriod billingPeriod;
    private List<Long> subscriptionIds;
}

