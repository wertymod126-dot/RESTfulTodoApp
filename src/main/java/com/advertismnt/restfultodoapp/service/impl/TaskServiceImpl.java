package com.advertismnt.restfultodoapp.service.impl;

import com.advertismnt.restfultodoapp.dto.TaskRequestDto;
import com.advertismnt.restfultodoapp.dto.TaskResponseDto;
import com.advertismnt.restfultodoapp.exception.ResourceNotFoundException;
import com.advertismnt.restfultodoapp.model.Priority;
import com.advertismnt.restfultodoapp.model.Task;
import com.advertismnt.restfultodoapp.repository.TaskRepository;
import com.advertismnt.restfultodoapp.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto request) {
        Task task = new Task();
        applyRequest(task, request);
        return toResponse(taskRepository.save(task));
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDto getTaskById(Long taskId) {
        return toResponse(findOrThrow(taskId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDto> getTasksByStatus(boolean completed) {
        return taskRepository.findByCompleted(completed).stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public TaskResponseDto updateTask(Long taskId, TaskRequestDto request) {
        Task task = findOrThrow(taskId);
        applyRequest(task, request);
        return toResponse(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.delete(findOrThrow(taskId));
    }

    // ---------- helpers ----------

    private Task findOrThrow(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + taskId));
    }

    private void applyRequest(Task task, TaskRequestDto request) {
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setPriority(request.priority() != null ? request.priority() : Priority.MEDIUM);
    }

    private TaskResponseDto toResponse(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted(),
                task.getDueDate(),
                task.getPriority());
    }
}