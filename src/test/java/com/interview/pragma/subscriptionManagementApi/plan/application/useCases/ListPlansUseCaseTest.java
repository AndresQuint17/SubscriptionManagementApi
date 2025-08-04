package com.interview.pragma.subscriptionManagementApi.plan.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanResponseDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.mappers.PlanDtoMapper;
import com.interview.pragma.subscriptionManagementApi.plan.domain.model.Plan;
import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;
import com.interview.pragma.subscriptionManagementApi.plan.domain.ports.repository.IPlanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListPlansUseCaseTest {

    private IPlanRepository repository;
    private PlanDtoMapper mapper;
    private ListPlansUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(IPlanRepository.class);
        mapper = new PlanDtoMapper();
        useCase = new ListPlansUseCase(repository, mapper);
    }

    @Test
    void shouldReturnListOfPlans() {
        Plan plan1 = new Plan(1L, "Basic", "Basic plan", new BigDecimal("9.99"), EBillingPeriod.MONTHLY, null);
        Plan plan2 = new Plan(2L, "Premium", "Premium plan", new BigDecimal("19.99"), EBillingPeriod.MONTHLY, null);

        when(repository.findAll()).thenReturn(List.of(plan1, plan2));

        List<PlanResponseDto> result = useCase.execute();

        assertNotNull(result);
        assertEquals(2, result.size());

        PlanResponseDto dto1 = result.get(0);
        assertEquals("Basic", dto1.getName());
        assertEquals(0, dto1.getPrice().compareTo(new BigDecimal("9.99")));
        assertEquals(EBillingPeriod.MONTHLY, dto1.getBillingPeriod());

        PlanResponseDto dto2 = result.get(1);
        assertEquals("Premium", dto2.getName());
        assertEquals(0, dto2.getPrice().compareTo(new BigDecimal("19.99")));
        assertEquals(EBillingPeriod.MONTHLY, dto2.getBillingPeriod());
    }

    @Test
    void shouldReturnEmptyListWhenNoPlansExist() {
        when(repository.findAll()).thenReturn(List.of());

        List<PlanResponseDto> result = useCase.execute();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
