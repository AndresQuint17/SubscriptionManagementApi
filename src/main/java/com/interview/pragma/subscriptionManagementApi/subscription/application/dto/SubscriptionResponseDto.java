package com.interview.pragma.subscriptionManagementApi.subscription.application.dto;

import com.interview.pragma.subscriptionManagementApi.subscription.domain.enums.ESubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SubscriptionResponseDto {
    private Long id;
    private Long userId;
    private Long planId;
    private LocalDate startDate;
    private LocalDate endDate;
    private ESubscriptionStatus status;
}
