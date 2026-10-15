package com.advertismnt.restfultodoapp.dto;

import com.advertismnt.restfultodoapp.model.Priority;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskRequestDto(
        @NotBlank(message = "Title is required")
        @Size(max = 100) String title,
        @Size(max = 500) String description,
        @FutureOrPresent LocalDate dueDate,
        Priority priority
) {}