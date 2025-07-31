package com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.persistence.mappers;

import com.interview.pragma.subscriptionManagementApi.subscription.domain.model.Subscription;
import com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.persistence.entities.SubscriptionEntity;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionEntityMapper {

    public Subscription toDomain(SubscriptionEntity entity) {
        return new Subscription(
                entity.getId(),
                entity.getUser().getId(),
                entity.getPlan().getId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getStatus()
        );
    }

    public SubscriptionEntity toEntity(Subscription domain) {
        throw new UnsupportedOperationException("Use service method to assemble entity from IDs (requires fetching UserEntity and PlanEntity).");
    }
}
