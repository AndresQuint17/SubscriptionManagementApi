package com.interview.pragma.subscriptionManagementApi.user.application.useCases;

import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserRequestDto;
import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserResponseDto;
import com.interview.pragma.subscriptionManagementApi.user.application.mappers.UserDtoMapper;
import com.interview.pragma.subscriptionManagementApi.user.domain.ports.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final IUserRepository userRepository;
    private final UserDtoMapper dtoMapper;

    public UserResponseDto execute(UserRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        var user = dtoMapper.toDomain(dto);
        var saved = userRepository.save(user);
        return dtoMapper.toResponseDto(saved);
    }
}
