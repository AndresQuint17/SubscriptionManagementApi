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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateSubscriptionUseCaseTest {

    private UpdateSubscriptionUseCase useCase;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IPlanRepository planRepository;

    @Mock
    private SubscriptionDtoMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new UpdateSubscriptionUseCase(subscriptionRepository, userRepository, planRepository, mapper);
    }

    @Test
    void shouldUpdateSubscriptionSuccessfully() {
        Long id = 1L;
        LocalDate startDate = LocalDate.now();
        Subscription existing = new Subscription(id, 10L, 20L, startDate, null, ESubscriptionStatus.ACTIVE);

        SubscriptionRequestDto dto = new SubscriptionRequestDto(10L, 20L, startDate, null, ESubscriptionStatus.CANCELLED);

        Subscription updated = new Subscription(id, 10L, 20L, startDate, null, ESubscriptionStatus.CANCELLED);

        SubscriptionResponseDto expected = new SubscriptionResponseDto(id, 10L, 20L, startDate, null, ESubscriptionStatus.CANCELLED);

        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(existing));
        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(new User(dto.getUserId(), "Name", "email", "pass", true, List.of())));
        when(planRepository.findById(dto.getPlanId())).thenReturn(Optional.of(new Plan(dto.getPlanId(), "Plan", "desc", null, null, null)));
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(updated);
        when(mapper.toResponseDto(updated)).thenReturn(expected);

        SubscriptionResponseDto result = useCase.execute(id, dto);

        assertEquals(expected, result);
    }

    @Test
    void shouldThrowIfSubscriptionNotFound() {
        Long id = 1L;
        SubscriptionRequestDto dto = new SubscriptionRequestDto(1L, 2L, LocalDate.now(), null, ESubscriptionStatus.ACTIVE);

        when(subscriptionRepository.findById(id)).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> useCase.execute(id, dto));
        assertEquals("Subscription not found with ID: " + id, ex.getMessage());
    }

    @Test
    void shouldThrowIfStartDateModified() {
        Long id = 1L;
        Subscription existing = new Subscription(id, 1L, 1L, LocalDate.now(), null, ESubscriptionStatus.ACTIVE);
        SubscriptionRequestDto dto = new SubscriptionRequestDto(1L, 1L, LocalDate.now().minusDays(1), null, ESubscriptionStatus.ACTIVE);

        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(existing));

        Exception ex = assertThrows(IllegalArgumentException.class, () -> useCase.execute(id, dto));
        assertEquals("Start date cannot be modified after creation.", ex.getMessage());
    }

    @Test
    void shouldThrowIfEndDateModified() {
        Long id = 1L;
        LocalDate today = LocalDate.now();
        Subscription existing = new Subscription(id, 1L, 1L, today, today, ESubscriptionStatus.ACTIVE);
        SubscriptionRequestDto dto = new SubscriptionRequestDto(1L, 1L, today, today.plusDays(1), ESubscriptionStatus.ACTIVE);

        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(existing));

        Exception ex = assertThrows(IllegalArgumentException.class, () -> useCase.execute(id, dto));
        assertEquals("End date cannot be modified once set.", ex.getMessage());
    }

    @Test
    void shouldThrowIfUserNotFound() {
        Long id = 1L;
        LocalDate today = LocalDate.now();
        Subscription existing = new Subscription(id, 1L, 1L, today, null, ESubscriptionStatus.ACTIVE);
        SubscriptionRequestDto dto = new SubscriptionRequestDto(999L, 1L, today, null, ESubscriptionStatus.CANCELLED);

        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(existing));
        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                useCase.execute(id, dto));
        assertEquals("User not found with ID: " + dto.getUserId(), ex.getMessage());
    }

    @Test
    void shouldThrowIfPlanNotFound() {
        Long id = 1L;
        LocalDate today = LocalDate.now();
        Subscription existing = new Subscription(id, 1L, 1L, today, null, ESubscriptionStatus.ACTIVE);
        SubscriptionRequestDto dto = new SubscriptionRequestDto(1L, 999L, today, null, ESubscriptionStatus.CANCELLED);

        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(existing));
        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(new User(null, null, null, null, false, null)));
        when(planRepository.findById(dto.getPlanId())).thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                useCase.execute(id, dto));
        assertEquals("Plan not found with ID: " + dto.getPlanId(), ex.getMessage());
    }

    @Test
    void shouldThrowIfStatusNull() {
        Long id = 1L;
        LocalDate today = LocalDate.now();
        Subscription existing = new Subscription(id, 1L, 1L, today, null, ESubscriptionStatus.ACTIVE);
        SubscriptionRequestDto dto = new SubscriptionRequestDto(1L, 1L, today, null, null);

        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(existing));
        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(new User(null, null, null, null, false, null)));
        when(planRepository.findById(dto.getUserId())).thenReturn(Optional.of(new Plan(null, null, null, null, null, null)));

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                useCase.execute(id, dto));
        assertEquals("Subscription status must be provided.", ex.getMessage());
    }
}
