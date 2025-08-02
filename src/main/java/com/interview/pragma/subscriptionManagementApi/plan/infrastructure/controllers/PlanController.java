package com.interview.pragma.subscriptionManagementApi.plan.infrastructure.controllers;

import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanRequestDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanResponseDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.useCases.CreatePlanUseCase;
import com.interview.pragma.subscriptionManagementApi.plan.application.useCases.DeletePlanUseCase;
import com.interview.pragma.subscriptionManagementApi.plan.application.useCases.GetPlanByIdUseCase;
import com.interview.pragma.subscriptionManagementApi.plan.application.useCases.ListPlansUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
@Tag(name = "Plans", description = "Operations related to subscription plans.")
public class PlanController {

    private final CreatePlanUseCase createPlanUseCase;
    private final GetPlanByIdUseCase getPlanByIdUseCase;
    private final ListPlansUseCase listPlansUseCase;
    private final DeletePlanUseCase deletePlanUseCase;

    @PostMapping
    @Operation(summary = "Create a new plan")
    public ResponseEntity<PlanResponseDto> createPlan(@Valid @RequestBody PlanRequestDto dto) {
        return ResponseEntity.ok(createPlanUseCase.execute(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get plan by ID.")
    public ResponseEntity<PlanResponseDto> getPlan(@PathVariable Long id) {
        return ResponseEntity.ok(getPlanByIdUseCase.execute(id));
    }

    @GetMapping
    @Operation(summary = "List all plans.")
    public ResponseEntity<List<PlanResponseDto>> listPlans() {
        return ResponseEntity.ok(listPlansUseCase.execute());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete plan by ID.")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        deletePlanUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
