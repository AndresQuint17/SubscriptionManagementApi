package com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_role")
@IdClass(AuthRoleId.class)
@Getter
@Setter
@NoArgsConstructor
public class AuthRoleEntity {
    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Id
    @Column(nullable = false, length = 20)
    private String role;

    @Column(name = "granted_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime grantedDate;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private AuthEntity auth;

    public AuthRoleEntity(String username, String role, LocalDateTime grantedDate) {
        this.username = username;
        this.role = role;
        this.grantedDate = grantedDate;
    }
}
