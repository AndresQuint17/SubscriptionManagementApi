package com.interview.pragma.subscriptionManagementApi.user.application.useCases;

import com.interview.pragma.subscriptionManagementApi.user.domain.model.User;
import com.interview.pragma.subscriptionManagementApi.user.domain.ports.repository.IUserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteUserUseCaseTest {

    private final IUserRepository userRepository = mock(IUserRepository.class);
    private final DeleteUserUseCase useCase = new DeleteUserUseCase(userRepository);

    @Test
    void shouldDeleteUserWhenExists() {
        Long userId = 1L;
        User user = new User(userId, "Alice", "alice@example.com", "password", true, List.of());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        useCase.execute(userId);

        verify(userRepository).deleteById(userId);
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        Long userId = 2L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(userId)
        );

        assertEquals("User not found with id: " + userId, exception.getMessage());
        verify(userRepository, never()).deleteById(any());
    }
}
