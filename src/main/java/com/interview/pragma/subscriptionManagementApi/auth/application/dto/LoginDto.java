package com.interview.pragma.subscriptionManagementApi.auth.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Request to login in the system.")
public class LoginDto {
    @Schema(example = "client", description = "Name of the user who wants to log in.")
    private String username;
    @Schema(example = "default#client!Password", description = "Password of the user who wants to log in.")
    private String password;
}
