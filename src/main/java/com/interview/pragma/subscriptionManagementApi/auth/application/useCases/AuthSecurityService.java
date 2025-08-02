package com.interview.pragma.subscriptionManagementApi.auth.application.useCases;

import com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.entities.AuthEntity;
import com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.entities.AuthRoleEntity;
import com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.repository.IAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthSecurityService implements UserDetailsService {
    private final IAuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthEntity auth = this.authRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        String[] roles = auth.getRoles().stream().map(AuthRoleEntity::getRole).toArray(String[]::new);

        return User.builder()
                .username(auth.getUsername())
                .password(auth.getPassword())
                .roles(roles)
                .accountLocked(auth.getLooked())
                .disabled(auth.getDisabled())
                .build();
    }
}
