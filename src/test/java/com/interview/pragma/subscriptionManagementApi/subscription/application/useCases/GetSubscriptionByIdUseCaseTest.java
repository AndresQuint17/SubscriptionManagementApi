package com.interview.pragma.subscriptionManagementApi.subscription.application.useCases;

import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionResponseDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.mappers.SubscriptionDtoMapper;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.model.Subscription;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.ports.repository.ISubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetSubscriptionByIdUseCaseTest {

    private ISubscriptionRepository repository;
    private SubscriptionDtoMapper mapper;
    private GetSubscriptionByIdUseCase useCase;

    @BeforeEach
    void setUp() {
        repository = mock(ISubscriptionRepository.class);
        mapper = mock(SubscriptionDtoMapper.class);
        useCase = new GetSubscriptionByIdUseCase(repository, mapper);
    }

    @Test
    void shouldReturnSubscriptionWhenIdExists() {
        Long id = 1L;
        Subscription subscription = new Subscription(null, null, null, null, null, null);
        SubscriptionResponseDto expectedDto = new SubscriptionResponseDto(null, null, null, null, null, null);

        when(repository.findById(id)).thenReturn(Optional.of(subscription));
        when(mapper.toResponseDto(subscription)).thenReturn(expectedDto);

        SubscriptionResponseDto result = useCase.execute(id);

        assertEquals(expectedDto, result);
        verify(repository).findById(id);
        verify(mapper).toResponseDto(subscription);
    }

    @Test
    void shouldThrowExceptionWhenIdDoesNotExist() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.execute(id));
        assertEquals("Subscription not found", exception.getMessage());
        verify(repository).findById(id);
        verify(mapper, never()).toResponseDto(any());
    }
}