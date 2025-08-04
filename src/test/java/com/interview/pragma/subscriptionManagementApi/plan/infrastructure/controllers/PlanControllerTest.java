package com.interview.pragma.subscriptionManagementApi.plan.infrastructure.controllers;

import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanRequestDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.dto.PlanResponseDto;
import com.interview.pragma.subscriptionManagementApi.plan.application.useCases.*;
import com.interview.pragma.subscriptionManagementApi.plan.domain.enums.EBillingPeriod;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlanController.class)
@Import(PlanControllerTest.TestConfig.class)
class PlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired private CreatePlanUseCase createPlanUseCase;
    @Autowired private GetPlanByIdUseCase getPlanByIdUseCase;
    @Autowired private ListPlansUseCase listPlansUseCase;
    @Autowired private DeletePlanUseCase deletePlanUseCase;

    @Configuration
    static class TestConfig {
        @Bean public CreatePlanUseCase createPlanUseCase() {
            return Mockito.mock(CreatePlanUseCase.class);
        }

        @Bean public GetPlanByIdUseCase getPlanByIdUseCase() {
            return Mockito.mock(GetPlanByIdUseCase.class);
        }

        @Bean public ListPlansUseCase listPlansUseCase() {
            return Mockito.mock(ListPlansUseCase.class);
        }

        @Bean public DeletePlanUseCase deletePlanUseCase() {
            return Mockito.mock(DeletePlanUseCase.class);
        }
    }

    @Test
    void shouldCreatePlan() throws Exception {
        PlanRequestDto requestDto = new PlanRequestDto("Pro", "Full access", new BigDecimal("29.99"), EBillingPeriod.YEARLY);
        PlanResponseDto responseDto = new PlanResponseDto(1L, "Pro", "Full access", new BigDecimal("29.99"), EBillingPeriod.YEARLY, null);

        when(createPlanUseCase.execute(any())).thenReturn(responseDto);

        mockMvc.perform(post("/api/plans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Pro"))
                .andExpect(jsonPath("$.price").value(29.99));
    }

    @Test
    void shouldReturnListOfPlans() throws Exception {
        PlanResponseDto dto1 = new PlanResponseDto(1L, "Basic", "Access", new BigDecimal("10.0"), EBillingPeriod.MONTHLY, null);
        PlanResponseDto dto2 = new PlanResponseDto(2L, "Pro", "Full", new BigDecimal("29.99"), EBillingPeriod.YEARLY, null);

        when(listPlansUseCase.execute()).thenReturn(List.of(dto1, dto2));

        mockMvc.perform(get("/api/plans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].price").value(10.00))
                .andExpect(jsonPath("$[1].price").value(29.99));
    }
}
