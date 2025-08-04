package com.interview.pragma.subscriptionManagementApi.plan.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;
import com.interview.pragma.subscriptionManagementApi.plan.domain.model.Plan;
import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanRequestDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanResponseDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.mappers.PlanDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreatePlanUseCaseTest {

    private IPlanRepository planRepository;
    private PlanDtoMapper mapper;
    private CreatePlanUseCase createPlanUseCase;

    @BeforeEach
    void setUp() {
        planRepository = mock(IPlanRepository.class);
        mapper = new PlanDtoMapper();
        createPlanUseCase = new CreatePlanUseCase(planRepository, mapper);
    }

    @Test
    void shouldCreatePlanSuccessfully() {
        PlanRequestDto dto = new PlanRequestDto("Basic", "Access", new BigDecimal("10.0"), EBillingPeriod.MONTHLY);
        Plan plan = mapper.toDomain(dto);
        Plan savedPlan = new Plan(1L, plan.getName(), plan.getDescription(), plan.getPrice(), plan.getBillingPeriod(), null);

        when(planRepository.existsByName("Basic")).thenReturn(false);
        when(planRepository.save(any(Plan.class))).thenReturn(savedPlan);

        PlanResponseDto response = createPlanUseCase.execute(dto);

        assertNotNull(response);
        assertEquals("Basic", response.getName());
        verify(planRepository).save(any(Plan.class));
    }

    @Test
    void shouldThrowExceptionWhenNameExists() {
        PlanRequestDto dto = new PlanRequestDto("Basic", "Access", new BigDecimal("10.0"), EBillingPeriod.MONTHLY);
        when(planRepository.existsByName("Basic")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> createPlanUseCase.execute(dto));
    }
}
