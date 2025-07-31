package com.interview.pragma.subscriptionManagementApi.user.application.useCases;

import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserResponseDto;
import com.interview.pragma.subscriptionManagementApi.user.application.mappers.UserDtoMapper;
import com.interview.pragma.subscriptionManagementApi.user.domain.ports.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCase {

    private final IUserRepository userRepository;
    private final UserDtoMapper dtoMapper;

    public UserResponseDto execute(Long id) {
        return userRepository.findById(id)
                .map(dtoMapper::toResponseDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }
}
