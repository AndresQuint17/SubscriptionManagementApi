package com.interview.pragma.subscriptionManagementApi.subscription.application.useCases;

import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionResponseDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.mappers.SubscriptionDtoMapper;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.enums.ESubscriptionStatus;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.model.Subscription;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.ports.repository.ISubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListSubscriptionsUseCaseTest {

    private ISubscriptionRepository subscriptionRepository;
    private SubscriptionDtoMapper mapper;
    private ListSubscriptionsUseCase useCase;

    @BeforeEach
    void setUp() {
        subscriptionRepository = mock(ISubscriptionRepository.class);
        mapper = mock(SubscriptionDtoMapper.class);
        useCase = new ListSubscriptionsUseCase(subscriptionRepository, mapper);
    }

    @Test
    void shouldReturnListOfSubscriptions() {
        Subscription subscription1 = new Subscription(1L, 1L, 1L, LocalDate.now(), null, ESubscriptionStatus.ACTIVE);
        Subscription subscription2 = new Subscription(2L, 2L, 2L, LocalDate.now(), null, ESubscriptionStatus.CANCELLED);

        SubscriptionResponseDto dto1 = new SubscriptionResponseDto(1L, 1L, 1L, LocalDate.now(), null, ESubscriptionStatus.ACTIVE);
        SubscriptionResponseDto dto2 = new SubscriptionResponseDto(2L, 2L, 2L, LocalDate.now(), null, ESubscriptionStatus.CANCELLED);

        when(subscriptionRepository.findAll()).thenReturn(Arrays.asList(subscription1, subscription2));
        when(mapper.toResponseDto(subscription1)).thenReturn(dto1);
        when(mapper.toResponseDto(subscription2)).thenReturn(dto2);

        List<SubscriptionResponseDto> result = useCase.execute();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
        verify(subscriptionRepository, times(1)).findAll();
        verify(mapper, times(1)).toResponseDto(subscription1);
        verify(mapper, times(1)).toResponseDto(subscription2);
    }
}
