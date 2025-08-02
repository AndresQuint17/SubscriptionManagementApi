package com.interview.pragma.subscriptionManagementApi.user.infrastructure.controllers;

import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserRequestDto;
import com.interview.pragma.subscriptionManagementApi.user.application.dto.UserResponseDto;
import com.interview.pragma.subscriptionManagementApi.user.application.useCases.CreateUserUseCase;
import com.interview.pragma.subscriptionManagementApi.user.application.useCases.DeleteUserUseCase;
import com.interview.pragma.subscriptionManagementApi.user.application.useCases.GetUserByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User-related operations")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(createUserUseCase.execute(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(getUserByIdUseCase.execute(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
