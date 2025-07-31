package com.interview.pragma.subscriptionManagementApi.user.application.mappers;

import com.interview.pragma.subscriptionManagementApi.user.domain.model.User;
import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserRequestDto;
import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

    public User toDomain(UserRequestDto dto) {
        return new User(
                null,                        // ID lo genera el sistema
                dto.getName(),
                dto.getEmail(),
                dto.getPassword(),
                true,                        // enabled por defecto
                null                         // subscriptions opcional, no se setean desde el request
        );
    }

    public UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.isEnabled(),
                user.getSubscriptionIds().orElse(null)
        );
    }
}
