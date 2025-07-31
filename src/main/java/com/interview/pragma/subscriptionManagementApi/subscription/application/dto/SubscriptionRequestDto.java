package com.interview.pragma.subscriptionManagementApi.subscription.application.dto;

import com.interview.pragma.subscriptionManagementApi.subscription.domain.enums.ESubscriptionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SubscriptionRequestDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long planId;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate; // opcional

    @NotNull
    private ESubscriptionStatus status;
}
