package com.interview.pragma.subscriptionManagementApi.plan.infrastructure.persistence.entities;

import com.interview.pragma.subscriptionManagementApi.shared.domain.entities.BaseEntity;
import com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.persistence.entities.SubscriptionEntity;
import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "plans", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Getter
@Setter
public class PlanEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EBillingPeriod billingPeriod;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubscriptionEntity> subscriptions;

}