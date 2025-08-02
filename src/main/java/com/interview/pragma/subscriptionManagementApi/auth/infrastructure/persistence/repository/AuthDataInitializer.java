package com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.repository;

import com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.entities.AuthEntity;
import com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.entities.AuthRoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AuthDataInitializer implements CommandLineRunner {

    private final IAuthRepository authRepository;
    private final IAuthRoleRepository authRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        try {
            if (authRepository.count() == 0) {
                AuthEntity admin = new AuthEntity();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setLooked(false);
                admin.setDisabled(false);

                authRepository.save(admin);

                authRoleRepository.save(new AuthRoleEntity("admin", "ADMIN", LocalDateTime.now()));

                AuthEntity client = new AuthEntity();
                client.setUsername("client");
                client.setPassword(passwordEncoder.encode("default#client!Password"));
                client.setLooked(false);
                client.setDisabled(false);

                authRepository.save(client);

                authRoleRepository.save(new AuthRoleEntity("client", "CLIENT", LocalDateTime.now()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Authentications couldn't upload.", e);
        }
    }
}
