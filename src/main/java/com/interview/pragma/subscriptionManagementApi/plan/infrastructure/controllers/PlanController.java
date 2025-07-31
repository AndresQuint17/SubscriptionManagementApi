package com.interview.pragma.subscriptionManagementApi.plan.infrastructure.controllers;

import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanRequestDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanResponseDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.useCases.CreatePlanUseCase;
import com.interview.pragma.subscriptionManagementApi.plan.application.useCases.DeletePlanUseCase;
import com.interview.pragma.subscriptionManagementApi.plan.application.useCases.GetPlanByIdUseCase;
import com.interview.pragma.subscriptionManagementApi.plan.application.useCases.ListPlansUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final CreatePlanUseCase createPlanUseCase;
    private final GetPlanByIdUseCase getPlanByIdUseCase;
    private final ListPlansUseCase listPlansUseCase;
    private final DeletePlanUseCase deletePlanUseCase;

    @PostMapping
    public ResponseEntity<PlanResponseDto> createPlan(@Valid @RequestBody PlanRequestDto dto) {
        return ResponseEntity.ok(createPlanUseCase.execute(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> getPlan(@PathVariable Long id) {
        return ResponseEntity.ok(getPlanByIdUseCase.execute(id));
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> listPlans() {
        return ResponseEntity.ok(listPlansUseCase.execute());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        deletePlanUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
