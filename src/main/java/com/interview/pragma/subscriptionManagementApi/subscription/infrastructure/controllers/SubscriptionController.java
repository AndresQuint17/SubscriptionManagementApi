package com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.controllers;

import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionRequestDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionResponseDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.useCases.CreateSubscriptionUseCase;
import com.interview.pragma.subscriptionManagementApi.subscription.application.useCases.DeleteSubscriptionUseCase;
import com.interview.pragma.subscriptionManagementApi.subscription.application.useCases.GetSubscriptionByIdUseCase;
import com.interview.pragma.subscriptionManagementApi.subscription.application.useCases.ListSubscriptionsUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final CreateSubscriptionUseCase createUseCase;
    private final GetSubscriptionByIdUseCase getByIdUseCase;
    private final ListSubscriptionsUseCase listUseCase;
    private final DeleteSubscriptionUseCase deleteUseCase;

    @PostMapping
    public ResponseEntity<SubscriptionResponseDto> create(@Valid @RequestBody SubscriptionRequestDto dto) {
        return ResponseEntity.ok(createUseCase.execute(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getByIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<SubscriptionResponseDto>> list() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
