package com.interview.pragma.subscriptionManagementApi.subscription.application.dto;

import com.interview.pragma.subscriptionManagementApi.subscription.domain.enums.ESubscriptionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Schema(description = "Service response to the request sent by the user.")
public class SubscriptionResponseDto {
    @Schema(example = "1", description = "Subscription ID")
    private Long id;
    @Schema(example = "1", description = "User ID")
    private Long userId;
    @Schema(example = "1", description = "Plan ID")
    private Long planId;
    @Schema(example = "2025-07-17", description = "Subscription start date")
    private LocalDate startDate;
    @Schema(example = "2026-16-17", description = "Subscription end date")
    private LocalDate endDate;
    @Schema(example = "ACTIVE", description = "Subscription status")
    private ESubscriptionStatus status;
}
