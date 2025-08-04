package com.interview.pragma.subscriptionManagementApi.user.application.useCases;

import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserRequestDto;
import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserResponseDto;
import com.interview.pragma.subscriptionManagementApi.user.application.mappers.UserDtoMapper;
import com.interview.pragma.subscriptionManagementApi.user.domain.model.User;
import com.interview.pragma.subscriptionManagementApi.user.domain.ports.repository.IUserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    private final IUserRepository userRepository = mock(IUserRepository.class);
    private final UserDtoMapper mapper = mock(UserDtoMapper.class);
    private final CreateUserUseCase useCase = new CreateUserUseCase(userRepository, mapper);

    @Test
    void shouldCreateUserSuccessfully() {
        UserRequestDto dto = new UserRequestDto("John", "john@example.com", "1234");
        User domainUser = new User(1L, "John", "john@example.com", "1234", true, List.of());
        UserResponseDto responseDto = new UserResponseDto(1L, "John", "john@example.com", true, List.of());

        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(mapper.toDomain(dto)).thenReturn(domainUser);
        when(userRepository.save(domainUser)).thenReturn(domainUser);
        when(mapper.toResponseDto(domainUser)).thenReturn(responseDto);

        UserResponseDto result = useCase.execute(dto);

        assertEquals(responseDto, result);
        verify(userRepository).existsByEmail(dto.getEmail());
        verify(userRepository).save(domainUser);
    }

    @Test
    void shouldThrowWhenEmailAlreadyExists() {
        UserRequestDto dto = new UserRequestDto("John", "john@example.com", "1234");

        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.execute(dto)
        );

        assertEquals("Email already exists.", exception.getMessage());
        verify(userRepository).existsByEmail(dto.getEmail());
        verify(userRepository, never()).save(any());
    }
}
