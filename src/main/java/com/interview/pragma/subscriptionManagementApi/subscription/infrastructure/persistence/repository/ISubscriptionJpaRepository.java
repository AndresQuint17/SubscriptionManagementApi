package com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.persistence.repository;

import com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.persistence.entities.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubscriptionJpaRepository extends JpaRepository<SubscriptionEntity, Long> {
}
