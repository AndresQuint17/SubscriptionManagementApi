package com.interview.pragma.subscriptionManagementApi.user.application.useCases;

import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserRequestDto;
import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserResponseDto;
import com.interview.pragma.subscriptionManagementApi.user.application.mappers.UserDtoMapper;
import com.interview.pragma.subscriptionManagementApi.user.domain.model.User;
import com.interview.pragma.subscriptionManagementApi.user.domain.ports.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final IUserRepository repository;
    private final UserDtoMapper mapper;

    public UserResponseDto execute(Long id, UserRequestDto dto) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        // Validar que el nuevo email no esté en uso por otro usuario
        repository.findByEmail(dto.getEmail())
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new IllegalArgumentException("Email already in use by another user");
                });

        // Validación: suscripciones deben estar presentes
        List<Long> subscriptions = existingUser.getSubscriptionIds()
                .orElseThrow(() -> new IllegalStateException("Subscriptions are required to update the Plan"));

        // Crear una nueva instancia con los datos actualizados
        User updatedUser = new User(
                existingUser.getId(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                existingUser.isEnabled(),
                subscriptions
        );

        return mapper.toResponseDto(repository.save(updatedUser));
    }
}
