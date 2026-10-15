package com.advertismnt.restfultodoapp.service;

import com.advertismnt.restfultodoapp.dto.TaskRequestDto;
import com.advertismnt.restfultodoapp.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto request);
    TaskResponseDto getTaskById(Long taskId);
    List<TaskResponseDto> getAllTasks();               // no parameter
    List<TaskResponseDto> getTasksByStatus(boolean completed);
    TaskResponseDto updateTask(Long taskId, TaskRequestDto request);
    void deleteTask(Long taskId);
}