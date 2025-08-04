package com.interview.pragma.subscriptionManagementApi.plan.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanResponseDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.mappers.PlanDtoMapper;
import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;
import com.interview.pragma.subscriptionManagementApi.plan.domain.model.Plan;
import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetPlanByIdUseCaseTest {

    private IPlanRepository repository;
    private PlanDtoMapper mapper;
    private GetPlanByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(IPlanRepository.class);
        mapper = new PlanDtoMapper();
        useCase = new GetPlanByIdUseCase(repository, mapper);
    }

    @Test
    void shouldReturnPlanWhenIdExists() {
        Long planId = 1L;
        Plan plan = new Plan(planId, "Pro", "Full access", new BigDecimal("29.99"), EBillingPeriod.YEARLY, null);
        when(repository.findById(planId)).thenReturn(Optional.of(plan));

        PlanResponseDto result = useCase.execute(planId);

        assertNotNull(result);
        assertEquals("Pro", result.getName());
        assertEquals("Full access", result.getDescription());
        assertEquals(29.99, Double.parseDouble(result.getPrice().toString()));
        assertEquals(EBillingPeriod.YEARLY, result.getBillingPeriod());
    }

    @Test
    void shouldThrowExceptionWhenPlanNotFound() {
        Long planId = 2L;
        when(repository.findById(planId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(planId);
        });

        assertEquals("Plan not found with id: " + planId, exception.getMessage());
    }
}