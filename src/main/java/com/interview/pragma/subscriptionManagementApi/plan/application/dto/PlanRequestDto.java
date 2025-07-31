package com.interview.pragma.subscriptionManagementApi.plan.application.dto;

import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlanRequestDto {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private EBillingPeriod billingPeriod;
}

