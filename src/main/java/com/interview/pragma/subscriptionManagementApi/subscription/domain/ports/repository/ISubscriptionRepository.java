package com.interview.pragma.subscriptionManagementApi.subscription.domain.ports.repository;

import com.interview.pragma.subscriptionManagementApi.subscription.domain.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface ISubscriptionRepository {
    Subscription save(Subscription subscription);
    Optional<Subscription> findById(Long id);
    List<Subscription> findAll();
    void deleteById(Long id);
}
