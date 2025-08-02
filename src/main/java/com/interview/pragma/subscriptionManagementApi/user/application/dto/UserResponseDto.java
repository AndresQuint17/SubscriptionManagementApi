package com.interview.pragma.subscriptionManagementApi.user.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "Service response to the request sent by the user.")
public class UserResponseDto {
    @Schema(example = "1", description = "User ID")
    private Long id;
    @Schema(example = "Andres Quintero", description = "User name")
    private String name;
    @Schema(example = "andres.quintero@email.com", description = "User email")
    private String email;
    @Schema(example = "true", description = "User activation status")
    private boolean enabled;
    @Schema(example = "[1,2]", description = "User subscriptions")
    private List<Long> subscriptions;
}
