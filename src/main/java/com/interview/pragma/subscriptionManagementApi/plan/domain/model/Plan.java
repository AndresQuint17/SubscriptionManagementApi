package com.interview.pragma.subscriptionManagementApi.plan.domain.model;

import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class Plan {
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final EBillingPeriod billingPeriod;
    private final List<Long> subscriptionIds;

    public Optional<List<Long>> getSubscriptionIds() {
        return Optional.ofNullable(subscriptionIds);
    }
}
