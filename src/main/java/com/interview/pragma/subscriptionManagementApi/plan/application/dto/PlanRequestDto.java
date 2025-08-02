package com.interview.pragma.subscriptionManagementApi.plan.application.dto;

import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(description = "Request to register a new plan in the system.")
public class PlanRequestDto {

    @NotBlank
    @Schema(example = "Premium", description = "Plan name")
    private String name;

    @Schema(example = "Unlimited access to all features", description = "Plan description")
    private String description;

    @NotNull
    @Schema(example = "29.99", description = "Plan price")
    private BigDecimal price;

    @NotNull
    @Schema(example = "MONTHLY", description = "Plan billing period: MONTHLY or YEARLY")
    private EBillingPeriod billingPeriod;
}

