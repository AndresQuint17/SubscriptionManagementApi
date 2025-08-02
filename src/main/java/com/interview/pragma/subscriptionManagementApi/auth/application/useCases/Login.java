package com.interview.pragma.subscriptionManagementApi.auth.application.useCases;

import com.interview.pragma.subscriptionManagementApi.auth.application.dto.LoginDto;
import com.interview.pragma.subscriptionManagementApi.config.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Login {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public String login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        this.authenticationManager.authenticate(login);

        return this.jwtUtil.create(loginDto.getUsername());
    }
}
