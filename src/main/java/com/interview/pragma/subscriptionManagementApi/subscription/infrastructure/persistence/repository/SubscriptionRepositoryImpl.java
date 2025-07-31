package com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.persistence.repository;

import com.interview.pragma.subscriptionManagementApi.subscription.domain.model.Subscription;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.ports.repository.ISubscriptionRepository;
import com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.persistence.mappers.SubscriptionEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepositoryImpl implements ISubscriptionRepository {
    private final ISubscriptionJpaRepository jpaRepository;
    private final SubscriptionEntityMapper mapper;

    @Override
    public Subscription save(Subscription subscription) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(subscription)));
    }

    @Override
    public Optional<Subscription> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Subscription> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
