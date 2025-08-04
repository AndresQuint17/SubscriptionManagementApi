package com.interview.pragma.subscriptionManagementApi.user.application.useCases;

import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserResponseDto;
import com.interview.pragma.subscriptionManagementApi.user.application.mappers.UserDtoMapper;
import com.interview.pragma.subscriptionManagementApi.user.domain.model.User;
import com.interview.pragma.subscriptionManagementApi.user.domain.ports.repository.IUserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetUserByIdUseCaseTest {

    private final IUserRepository userRepository = mock(IUserRepository.class);
    private final UserDtoMapper mapper = mock(UserDtoMapper.class);
    private final GetUserByIdUseCase useCase = new GetUserByIdUseCase(userRepository, mapper);

    @Test
    void shouldReturnUserResponseDtoWhenUserExists() {
        Long userId = 1L;
        User user = new User(userId, "John", "john@example.com", "pass123", true, List.of(1L, 2L));
        UserResponseDto responseDto = new UserResponseDto(userId, "John", "john@example.com", true, List.of(1L, 2L));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(mapper.toResponseDto(user)).thenReturn(responseDto);

        UserResponseDto result = useCase.execute(userId);

        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(userRepository).findById(userId);
        verify(mapper).toResponseDto(user);
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
        verify(userRepository).findById(userId);
        verify(mapper, never()).toResponseDto(any());
    }
}
