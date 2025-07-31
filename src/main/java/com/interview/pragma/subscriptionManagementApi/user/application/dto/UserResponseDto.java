package com.interview.pragma.subscriptionManagementApi.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private boolean enabled;
    private List<Long> subscriptions;
}
