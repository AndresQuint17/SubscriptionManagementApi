package com.interview.pragma.subscriptionManagementApi.user.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Request to register a new user in the system.")
public class UserRequestDto {

    @NotBlank
    @Schema(example = "Andres Quintero", description = "User name")
    private String name;

    @NotBlank
    @Email
    @Schema(example = "andres.quintero@email.com", description = "User email")
    private String email;

    @NotBlank
    @Schema(example = "us3r.pa$$_w0r!", description = "User password")
    private String password;
}
