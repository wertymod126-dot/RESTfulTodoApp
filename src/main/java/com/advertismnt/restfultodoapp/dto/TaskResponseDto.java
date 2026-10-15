package com.advertismnt.restfultodoapp.dto;


import com.advertismnt.restfultodoapp.model.Priority;

import java.time.LocalDate;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        boolean completed,
        LocalDate dueDate,
        Priority priority
) {}
