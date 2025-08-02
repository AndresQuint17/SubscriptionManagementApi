package com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.repository;

import com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.entities.AuthRoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface IAuthRoleRepository extends CrudRepository<AuthRoleEntity, String> {
}
