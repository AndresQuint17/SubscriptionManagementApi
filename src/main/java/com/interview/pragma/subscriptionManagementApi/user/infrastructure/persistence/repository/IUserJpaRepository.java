package com.interview.pragma.subscriptionManagementApi.user.infrastructure.persistence.repository;

import com.interview.pragma.subscriptionManagementApi.user.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
