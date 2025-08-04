package com.interview.pragma.subscriptionManagementApi.plan.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanRequestDto;
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

class UpdatePlanUseCaseTest {

    private IPlanRepository repository;
    private PlanDtoMapper mapper;
    private UpdatePlanUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(IPlanRepository.class);
        mapper = mock(PlanDtoMapper.class);
        useCase = new UpdatePlanUseCase(repository, mapper);
    }

    @Test
    void shouldUpdatePlanSuccessfully() {
        Long id = 1L;
        Plan existingPlan = new Plan(id, "Basic", "Basic Plan", new BigDecimal("9.99"), EBillingPeriod.MONTHLY, null);
        PlanRequestDto dto = new PlanRequestDto("Pro", "Pro Plan", new BigDecimal("19.99"), EBillingPeriod.MONTHLY);
        Plan updatedPlan = new Plan(id, dto.getName(), dto.getDescription(), dto.getPrice(), dto.getBillingPeriod(), null);
        Plan savedPlan = new Plan(id, dto.getName(), dto.getDescription(), dto.getPrice(), dto.getBillingPeriod(), null);
        PlanResponseDto responseDto = new PlanResponseDto(id, dto.getName(), dto.getDescription(), dto.getPrice(), dto.getBillingPeriod(), null);

        when(repository.findById(id)).thenReturn(Optional.of(existingPlan));
        when(repository.existsByName(dto.getName())).thenReturn(false);
        when(repository.save(any(Plan.class))).thenReturn(savedPlan);
        when(mapper.toResponseDto(savedPlan)).thenReturn(responseDto);

        PlanResponseDto result = useCase.execute(id, dto);

        assertNotNull(result);
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getDescription(), result.getDescription());
        assertEquals(0, dto.getPrice().compareTo(result.getPrice()));
        assertEquals(dto.getBillingPeriod(), result.getBillingPeriod());

        verify(repository).save(any(Plan.class));
        verify(mapper).toResponseDto(savedPlan);
    }

    @Test
    void shouldThrowExceptionIfPlanNotFound() {
        Long id = 2L;
        PlanRequestDto dto = new PlanRequestDto("Standard", "Standard plan", new BigDecimal("14.99"), EBillingPeriod.MONTHLY);

        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(id, dto));
        assertEquals("Plan not found with ID: " + id, exception.getMessage());

        verify(repository, never()).save(any());
        verify(mapper, never()).toResponseDto(any());
    }

    @Test
    void shouldThrowExceptionIfNameExistsForDifferentPlan() {
        Long id = 3L;
        Plan existingPlan = new Plan(id, "Basic", "Basic Plan", new BigDecimal("9.99"), EBillingPeriod.MONTHLY, null);
        PlanRequestDto dto = new PlanRequestDto("Premium", "Updated description", new BigDecimal("29.99"), EBillingPeriod.MONTHLY);

        when(repository.findById(id)).thenReturn(Optional.of(existingPlan));
        when(repository.existsByName(dto.getName())).thenReturn(true); // Name already exists

        Exception exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(id, dto));
        assertEquals("Plan name already exists", exception.getMessage());

        verify(repository, never()).save(any());
        verify(mapper, never()).toResponseDto(any());
    }
}