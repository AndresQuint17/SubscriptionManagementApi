package com.interview.pragma.subscriptionManagementApi.subscription.application.dto;

import com.interview.pragma.subscriptionManagementApi.subscription.domain.enums.ESubscriptionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Request to register a new user in the system.")
@AllArgsConstructor
public class SubscriptionRequestDto {

    @NotNull
    @Schema(example = "1", description = "User ID")
    private Long userId;

    @NotNull
    @Schema(example = "1", description = "Plan ID")
    private Long planId;

    @NotNull
    @Schema(example = "2025-07-17", description = "Subscription start date")
    private LocalDate startDate;

    @Schema(example = "2026-16-17", description = "Subscription end date")
    private LocalDate endDate; // opcional

    @NotNull
    @Schema(example = "ACTIVE", description = "Subscription status")
    private ESubscriptionStatus status;
}
