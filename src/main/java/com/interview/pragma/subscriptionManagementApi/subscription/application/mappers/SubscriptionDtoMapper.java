package com.interview.pragma.subscriptionManagementApi.subscription.application.mappers;

import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionRequestDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionResponseDto;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.model.Subscription;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionDtoMapper {

    public Subscription toDomain(SubscriptionRequestDto dto) {
        return new Subscription(
                null,
                dto.getUserId(),
                dto.getPlanId(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getStatus()
        );
    }

    public SubscriptionResponseDto toResponseDto(Subscription subscription) {
        return new SubscriptionResponseDto(
                subscription.getId(),
                subscription.getUserId(),
                subscription.getPlanId(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getStatus()
        );
    }
}
