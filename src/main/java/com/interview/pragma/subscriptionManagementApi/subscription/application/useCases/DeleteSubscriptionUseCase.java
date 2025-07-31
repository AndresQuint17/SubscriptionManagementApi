package com.interview.pragma.subscriptionManagementApi.subscription.application.useCases;

import com.interview.pragma.subscriptionManagementApi.subscription.domain.ports.repository.ISubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteSubscriptionUseCase {

    private final ISubscriptionRepository repository;

    public void execute(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Subscription not found");
        }
        repository.deleteById(id);
    }
}
