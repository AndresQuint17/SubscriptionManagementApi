package com.interview.pragma.subscriptionManagementApi.auth.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "auths", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
public class AuthEntity {
    @Id
    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 50)
    private String email;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean looked;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private Boolean disabled;

    @OneToMany(mappedBy = "username", fetch = FetchType.EAGER)
    private List<AuthRoleEntity> roles;
}
