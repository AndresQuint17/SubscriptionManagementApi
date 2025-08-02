package com.interview.pragma.subscriptionManagementApi.plan.application.dto;

import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "Service response to the request sent by the user.")
public class PlanResponseDto {
    @Schema(example = "1", description = "Plan ID")
    private Long id;
    @Schema(example = "Premium", description = "Plan name")
    private String name;
    @Schema(example = "Unlimited access to all features", description = "Plan description")
    private String description;
    @Schema(example = "29.99", description = "Plan price")
    private BigDecimal price;
    @Schema(example = "YEARLY", description = "Plan billing period")
    private EBillingPeriod billingPeriod;
    @Schema(example = "[1,3,8]", description = "List of plan subscriptions")
    private List<Long> subscriptionIds;
}

