package com.interview.pragma.subscriptionManagementApi.plan.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.domain.model.Plan;
import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;
import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeletePlanUseCaseTest {

    private IPlanRepository repository;
    private DeletePlanUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(IPlanRepository.class);
        useCase = new DeletePlanUseCase(repository);
    }

    @Test
    void shouldDeleteExistingPlan() {
        Long id = 1L;
        Plan plan = new Plan(id, "Standard", "Standard plan", new BigDecimal("14.99"), EBillingPeriod.MONTHLY, null);
        when(repository.findById(id)).thenReturn(Optional.of(plan));

        useCase.execute(id);

        verify(repository).deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenPlanNotFound() {
        Long id = 2L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(id));
        assertEquals("Plan not found with id: " + id, exception.getMessage());

        verify(repository, never()).deleteById(id);
    }
}
