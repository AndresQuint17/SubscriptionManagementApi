package com.interview.pragma.subscriptionManagementApi.subscription.application.useCases;

import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionResponseDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.mappers.SubscriptionDtoMapper;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.ports.repository.ISubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListSubscriptionsUseCase {

    private final ISubscriptionRepository repository;
    private final SubscriptionDtoMapper mapper;

    public List<SubscriptionResponseDto> execute() {
        return repository.findAll().stream().map(mapper::toResponseDto).toList();
    }
}
