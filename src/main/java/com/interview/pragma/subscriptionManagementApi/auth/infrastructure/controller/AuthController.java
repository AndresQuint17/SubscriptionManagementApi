package com.interview.pragma.subscriptionManagementApi.auth.infrastructure.controller;

import com.interview.pragma.subscriptionManagementApi.auth.application.dto.LoginDto;
import com.interview.pragma.subscriptionManagementApi.auth.application.useCases.Login;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Operations related to authentication in the system.")
public class AuthController {
    private final Login loginService;

    @PostMapping("/login")
    @Operation(summary = "Authenticate to the system with a username and password. The service responds with a JWT in the authentication header of the response if the data is correct.")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, this.loginService.login(loginDto)).build();
    }
}
