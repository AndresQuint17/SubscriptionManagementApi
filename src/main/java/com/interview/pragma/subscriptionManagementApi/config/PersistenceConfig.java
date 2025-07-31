package com.interview.pragma.subscriptionManagementApi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence",
        "com.interview.pragma.subscriptionManagementApi.user.infrastructure.persistence",
        "com.interview.pragma.subscriptionManagementApi.plan.infrastructure.persistence",
        "com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.persistence"
})
public class PersistenceConfig {
}
