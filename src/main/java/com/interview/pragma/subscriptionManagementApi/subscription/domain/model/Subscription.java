package com.interview.pragma.subscriptionManagementApi.subscription.domain.model;

import com.interview.pragma.subscriptionManagementApi.subscription.domain.enums.ESubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Subscription {
    private final Long id;
    private final Long userId;
    private final Long planId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final ESubscriptionStatus status;
}
