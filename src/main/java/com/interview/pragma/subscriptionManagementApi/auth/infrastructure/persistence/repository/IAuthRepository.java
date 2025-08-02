package com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.repository;

import com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.entities.AuthEntity;
import org.springframework.data.repository.CrudRepository;

public interface IAuthRepository extends CrudRepository<AuthEntity, String> {
}
