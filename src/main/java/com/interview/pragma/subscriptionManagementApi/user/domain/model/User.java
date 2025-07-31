package com.interview.pragma.subscriptionManagementApi.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class User {
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final boolean enabled;
    private final List<Long> subscriptionIds;

    public Optional<List<Long>> getSubscriptionIds() {
        return Optional.ofNullable(subscriptionIds);
    }
}
