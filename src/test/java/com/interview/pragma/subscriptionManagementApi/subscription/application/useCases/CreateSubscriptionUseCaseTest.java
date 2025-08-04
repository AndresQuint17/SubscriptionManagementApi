package com.interview.pragma.subscriptionManagementApi.subscription.application.useCases;

import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;
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
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateSubscriptionUseCaseTest {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IPlanRepository planRepository;

    @Mock
    private SubscriptionDtoMapper mapper;

    @InjectMocks
    private CreateSubscriptionUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateSubscriptionSuccessfully() {
        // Arrange
        Long userId = 1L;
        Long planId = 2L;

        SubscriptionRequestDto requestDto = new SubscriptionRequestDto(userId, planId, null, null, ESubscriptionStatus.ACTIVE);

        User user = new User(userId, "John Doe", "john@example.com", "pass", true, null);
        Plan plan = new Plan(planId, "Basic", "Desc", new BigDecimal("9.99"), EBillingPeriod.MONTHLY, null);
        Subscription subscription = new Subscription(null, userId, planId, LocalDate.now(), null, ESubscriptionStatus.ACTIVE);
        Subscription saved = new Subscription(100L, userId, planId, subscription.getStartDate(), null, ESubscriptionStatus.ACTIVE);
        SubscriptionResponseDto responseDto = new SubscriptionResponseDto(100L, userId, planId, subscription.getStartDate(), null, ESubscriptionStatus.ACTIVE);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(planRepository.findById(planId)).thenReturn(Optional.of(plan));
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(saved);
        when(mapper.toResponseDto(saved)).thenReturn(responseDto);

        // Act
        SubscriptionResponseDto result = useCase.execute(requestDto);

        // Assert
        assertNotNull(result);
        assertEquals(100L, result.getId());
        verify(userRepository).findById(userId);
        verify(planRepository).findById(planId);
        verify(subscriptionRepository).save(any(Subscription.class));
        verify(mapper).toResponseDto(saved);
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        // Arrange
        SubscriptionRequestDto requestDto = new SubscriptionRequestDto(1L, 2L, null, null, ESubscriptionStatus.ACTIVE);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> useCase.execute(requestDto));
        assertEquals("User not found", ex.getMessage());
        verify(userRepository).findById(1L);
        verify(planRepository, never()).findById(any());
    }

    @Test
    void shouldThrowWhenPlanNotFound() {
        // Arrange
        Long userId = 1L;
        Long planId = 2L;
        SubscriptionRequestDto requestDto = new SubscriptionRequestDto(userId, planId, null, null, ESubscriptionStatus.ACTIVE);
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User(null,null, null, null, false, null)));
        when(planRepository.findById(planId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class, () -> useCase.execute(requestDto));
        assertEquals("Plan not found", ex.getMessage());
        verify(userRepository).findById(userId);
        verify(planRepository).findById(planId);
    }
}