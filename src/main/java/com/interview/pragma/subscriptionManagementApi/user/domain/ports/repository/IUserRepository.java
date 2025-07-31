package com.interview.pragma.subscriptionManagementApi.user.domain.ports.repository;

import com.interview.pragma.subscriptionManagementApi.user.domain.model.User;

import java.util.Optional;

public interface IUserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteById(Long id);
}
