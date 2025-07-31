package com.interview.pragma.subscriptionManagementApi.plan.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePlanUseCase {

    private final IPlanRepository planRepository;

    public void execute(Long id) {
        if (planRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Plan not found with id: " + id);
        }
        planRepository.deleteById(id);
    }
}
