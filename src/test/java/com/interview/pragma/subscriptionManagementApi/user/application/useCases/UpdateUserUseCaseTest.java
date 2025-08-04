package com.interview.pragma.subscriptionManagementApi.user.application.useCases;

import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserRequestDto;
import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserResponseDto;
import com.interview.pragma.subscriptionManagementApi.user.application.mappers.UserDtoMapper;
import com.interview.pragma.subscriptionManagementApi.user.domain.model.User;
import com.interview.pragma.subscriptionManagementApi.user.domain.ports.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateUserUseCaseTest {

    private IUserRepository userRepository;
    private UserDtoMapper mapper;
    private UpdateUserUseCase updateUserUseCase;

    @BeforeEach
    void setUp() {
        userRepository = mock(IUserRepository.class);
        mapper = mock(UserDtoMapper.class);
        updateUserUseCase = new UpdateUserUseCase(userRepository, mapper);
    }

    @Test
    void shouldUpdateUserSuccessfully() {
        Long userId = 1L;

        User existingUser = new User(userId, "Old Name", "old@example.com", "password", true, List.of(101L));
        UserRequestDto requestDto = new UserRequestDto("New Name", "new@example.com", "newpassword");

        User updatedUser = new User(userId, "New Name", "new@example.com", "newpassword", true, List.of(101L));
        UserResponseDto responseDto = new UserResponseDto(userId, "New Name", "new@example.com", true, List.of(101L));

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail("new@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
        when(mapper.toResponseDto(updatedUser)).thenReturn(responseDto);

        UserResponseDto result = updateUserUseCase.execute(userId, requestDto);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals("new@example.com", result.getEmail());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        Long userId = 1L;
        UserRequestDto dto = new UserRequestDto("Name", "email@example.com", "password");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> updateUserUseCase.execute(userId, dto));

        assertEquals("User not found with ID: 1", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsAlreadyInUseByAnotherUser() {
        Long userId = 1L;
        User existingUser = new User(userId, "User", "email@example.com", "password", true, List.of(1L));
        User anotherUser = new User(2L, "Other", "new@example.com", "pwd", true, List.of(2L));

        UserRequestDto dto = new UserRequestDto("User", "new@example.com", "newpwd");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail("new@example.com")).thenReturn(Optional.of(anotherUser));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> updateUserUseCase.execute(userId, dto));

        assertEquals("Email already in use by another user", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSubscriptionsAreMissing() {
        Long userId = 1L;
        User existingUser = new User(userId, "User", "email@example.com", "password", true, null);
        UserRequestDto dto = new UserRequestDto("User", "new@example.com", "newpwd");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail("new@example.com")).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalStateException.class,
                () -> updateUserUseCase.execute(userId, dto));

        assertEquals("Subscriptions are required to update the Plan", exception.getMessage());
    }
}
