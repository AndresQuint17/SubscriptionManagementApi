package com.interview.pragma.subscriptionManagementApi.subscription.application.useCases;

import com.interview.pragma.subscriptionManagementApi.subscription.domain.model.Subscription;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.ports.repository.ISubscriptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteSubscriptionUseCaseTest {

    @Mock
    private ISubscriptionRepository repository;

    @InjectMocks
    private DeleteSubscriptionUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteSubscriptionWhenExists() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(mock(Subscription.class)));

        useCase.execute(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenSubscriptionNotFound() {
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(id));

        verify(repository, never()).deleteById(anyLong());
    }
}