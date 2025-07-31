package com.interview.pragma.subscriptionManagementApi.subscription.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionRequestDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionResponseDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.mappers.SubscriptionDtoMapper;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.model.Subscription;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.ports.repository.ISubscriptionRepository;
import com.interview.pragma.subscriptionManagementApi.user.domain.ports.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CreateSubscriptionUseCase {

    private final ISubscriptionRepository subscriptionRepository;
    private final IUserRepository userRepository;
    private final IPlanRepository planRepository;
    private final SubscriptionDtoMapper mapper;

    public SubscriptionResponseDto execute(SubscriptionRequestDto dto) {
        var user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));

        Subscription subscription = new Subscription(
                null,
                user.getId(),
                plan.getId(),
                LocalDate.now(),
                null,
                dto.getStatus()
        );

        var saved = subscriptionRepository.save(subscription);
        return mapper.toResponseDto(saved);
    }
}
