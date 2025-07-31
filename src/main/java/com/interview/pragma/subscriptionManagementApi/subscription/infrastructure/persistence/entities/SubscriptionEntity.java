package com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.persistence.entities;

import com.interview.pragma.subscriptionManagementApi.plan.infrastructure.persistence.entities.PlanEntity;
import com.interview.pragma.subscriptionManagementApi.subscription.domain.enums.ESubscriptionStatus;
import com.interview.pragma.subscriptionManagementApi.shared.domain.entities.BaseEntity;
import com.interview.pragma.subscriptionManagementApi.user.infrastructure.persistence.entities.UserEntity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
public class SubscriptionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_id", nullable = false)
    private PlanEntity plan;

    @Column(nullable = false, updatable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ESubscriptionStatus status;

}

