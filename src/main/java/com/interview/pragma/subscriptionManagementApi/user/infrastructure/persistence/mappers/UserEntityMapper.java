package com.interview.pragma.subscriptionManagementApi.user.infrastructure.persistence.mappers;

import com.interview.pragma.subscriptionManagementApi.user.domain.model.User;
import com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.persistence.entities.SubscriptionEntity;
import com.interview.pragma.subscriptionManagementApi.user.infrastructure.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityMapper {

    public User toDomain(UserEntity entity) {
        List<Long> subs = null;
        if (entity.getSubscriptions() != null) {
            subs = entity.getSubscriptions()
                    .stream()
                    .map(SubscriptionEntity::getId)
                    .collect(Collectors.toList());
        }

        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.isEnabled(),
                subs
        );
    }

    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setEnabled(user.isEnabled());

        // No seteamos subscriptions directamente aquí para evitar acoplamiento o ciclos
        return entity;
    }
}
