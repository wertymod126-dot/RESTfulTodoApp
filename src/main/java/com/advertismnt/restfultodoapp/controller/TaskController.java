package com.advertismnt.restfultodoapp.controller;


import com.advertismnt.restfultodoapp.dto.TaskRequestDto;
import com.advertismnt.restfultodoapp.dto.TaskResponseDto;
import com.advertismnt.restfultodoapp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> create(@Valid @RequestBody TaskRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    // GET /api/v1/tasks              -> all tasks
    // GET /api/v1/tasks?completed=true -> filtered
    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAll(
            @RequestParam(required = false) Boolean completed) {
        List<TaskResponseDto> result = (completed == null)
                ? taskService.getAllTasks()
                : taskService.getTasksByStatus(completed);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> update(@PathVariable Long id,
                                                  @Valid @RequestBody TaskRequestDto request) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
