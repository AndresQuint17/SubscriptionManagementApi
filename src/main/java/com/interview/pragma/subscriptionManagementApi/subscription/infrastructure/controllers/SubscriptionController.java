package com.interview.pragma.subscriptionManagementApi.subscription.infrastructure.controllers;

import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionRequestDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.dto.SubscriptionResponseDto;
import com.interview.pragma.subscriptionManagementApi.subscription.application.useCases.CreateSubscriptionUseCase;
import com.interview.pragma.subscriptionManagementApi.subscription.application.useCases.DeleteSubscriptionUseCase;
import com.interview.pragma.subscriptionManagementApi.subscription.application.useCases.GetSubscriptionByIdUseCase;
import com.interview.pragma.subscriptionManagementApi.subscription.application.useCases.ListSubscriptionsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Subscriptions", description = "Operations related to user subscriptions.")
public class SubscriptionController {

    private final CreateSubscriptionUseCase createUseCase;
    private final GetSubscriptionByIdUseCase getByIdUseCase;
    private final ListSubscriptionsUseCase listUseCase;
    private final DeleteSubscriptionUseCase deleteUseCase;

    @PostMapping
    @Operation(summary = "Subscribe a user to a plan.")
    public ResponseEntity<SubscriptionResponseDto> subscribe(@Valid @RequestBody SubscriptionRequestDto dto) {
        return ResponseEntity.ok(createUseCase.execute(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a subscription of a user by ID.")
    public ResponseEntity<SubscriptionResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(getByIdUseCase.execute(id));
    }

    @GetMapping
    @Operation(summary = "List all subscriptions.")
    public ResponseEntity<List<SubscriptionResponseDto>> list() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a subscription by id.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
