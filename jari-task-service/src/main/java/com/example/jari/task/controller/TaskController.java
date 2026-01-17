package com.example.jari.task.controller;

import com.example.jari.common.dto.ResponseDto;
import com.example.jari.task.dto.TaskDto;
import com.example.jari.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ResponseDto<TaskDto>> createTask(@Valid @RequestBody TaskDto taskDto) {
        TaskDto createdTask = taskService.createTask(taskDto);
        ResponseDto<TaskDto> response = ResponseDto.<TaskDto>builder()
                .success(true)
                .message("Task created successfully")
                .data(createdTask)
                .status(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<TaskDto>> getTaskById(@PathVariable Long id) {
        TaskDto task = taskService.getTaskById(id);
        ResponseDto<TaskDto> response = ResponseDto.<TaskDto>builder()
                .success(true)
                .message("Task retrieved successfully")
                .data(task)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/key/{key}")
    public ResponseEntity<ResponseDto<TaskDto>> getTaskByKey(@PathVariable String key) {
        TaskDto task = taskService.getTaskByKey(key);
        ResponseDto<TaskDto> response = ResponseDto.<TaskDto>builder()
                .success(true)
                .message("Task retrieved successfully")
                .data(task)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<TaskDto>>> getAllTasks() {
        List<TaskDto> tasks = taskService.getAllTasks();
        ResponseDto<List<TaskDto>> response = ResponseDto.<List<TaskDto>>builder()
                .success(true)
                .message("Tasks retrieved successfully")
                .data(tasks)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ResponseDto<List<TaskDto>>> getTasksByProjectId(@PathVariable Long projectId) {
        List<TaskDto> tasks = taskService.getTasksByProjectId(projectId);
        ResponseDto<List<TaskDto>> response = ResponseDto.<List<TaskDto>>builder()
                .success(true)
                .message("Tasks retrieved successfully")
                .data(tasks)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<ResponseDto<List<TaskDto>>> getTasksByAssigneeId(@PathVariable Long assigneeId) {
        List<TaskDto> tasks = taskService.getTasksByAssigneeId(assigneeId);
        ResponseDto<List<TaskDto>> response = ResponseDto.<List<TaskDto>>builder()
                .success(true)
                .message("Tasks retrieved successfully")
                .data(tasks)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<TaskDto>> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = taskService.updateTask(id, taskDto);
        ResponseDto<TaskDto> response = ResponseDto.<TaskDto>builder()
                .success(true)
                .message("Task updated successfully")
                .data(updatedTask)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        ResponseDto<Void> response = ResponseDto.<Void>builder()
                .success(true)
                .message("Task deleted successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
