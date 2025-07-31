package com.interview.pragma.subscriptionManagementApi.subscription.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.domain.model.Plan;
import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionRequestDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionResponseDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.mappers.SubscriptionDtoMapper;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.enums.ESubscriptionStatus;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.model.Subscription;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.ports.repository.ISubscriptionRepository;
import com.interview.pragma.subscriptionManagementApi.user.domain.model.User;
import com.interview.pragma.subscriptionManagementApi.user.domain.ports.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateSubscriptionUseCase {

    private final ISubscriptionRepository repository;
    private final IUserRepository userRepository;
    private final IPlanRepository planRepository;
    private final SubscriptionDtoMapper mapper;

    public SubscriptionResponseDto execute(Long id, SubscriptionRequestDto dto) {
        Subscription existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found with ID: " + id));

        // Validar: startDate no puede ser modificado
        if (!existing.getStartDate().equals(dto.getStartDate())) {
            throw new IllegalArgumentException("Start date cannot be modified after creation.");
        }

        // Validar: endDate no puede ser modificado si ya está seteado
        if (existing.getEndDate() != null && dto.getEndDate() != null &&
                !existing.getEndDate().equals(dto.getEndDate())) {
            throw new IllegalArgumentException("End date cannot be modified once set.");
        }

        // Validar usuario
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + dto.getUserId()));

        // Validar plan
        Plan plan = planRepository.findById(dto.getPlanId())
                .orElseThrow(() -> new IllegalArgumentException("Plan not found with ID: " + dto.getPlanId()));

        // Validar estado
        ESubscriptionStatus status = dto.getStatus();
        if (status == null) {
            throw new IllegalArgumentException("Subscription status must be provided.");
        }

        Subscription updated = new Subscription(
                existing.getId(),
                user.getId(),
                plan.getId(),
                existing.getStartDate(), // se conserva
                dto.getEndDate() != null ? dto.getEndDate() : existing.getEndDate(), // solo se setea si no existía
                status
        );

        return mapper.toResponseDto(repository.save(updated));
    }
}
