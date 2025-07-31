package com.interview.pragma.subscriptionManagementApi.plan.infrastructure.persistence.mappers;

import com.interview.pragma.subscriptionManagementApi.plan.domain.model.Plan;
import com.interview.pragma.subscriptionManagementApi.plan.infrastructure.persistence.entities.PlanEntity;
import com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.persistence.entities.SubscriptionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanEntityMapper {

    public Plan toDomain(PlanEntity entity) {
        List<Long> subs = null;
        if (entity.getSubscriptions() != null) {
            subs = entity.getSubscriptions()
                    .stream()
                    .map(SubscriptionEntity::getId)
                    .collect(Collectors.toList());
        }

        return new Plan(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getBillingPeriod(),
                subs
        );
    }

    public PlanEntity toEntity(Plan plan) {
        PlanEntity entity = new PlanEntity();
        entity.setId(plan.getId());
        entity.setName(plan.getName());
        entity.setDescription(plan.getDescription());
        entity.setPrice(plan.getPrice());
        entity.setBillingPeriod(plan.getBillingPeriod());

        // No seteamos subscriptions aquí directamente
        return entity;
    }
}
