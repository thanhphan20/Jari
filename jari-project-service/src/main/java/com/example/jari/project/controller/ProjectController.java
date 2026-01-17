package com.example.jari.project.controller;

import com.example.jari.common.dto.ResponseDto;
import com.example.jari.project.dto.ProjectDto;
import com.example.jari.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ResponseDto<ProjectDto>> createProject(@Valid @RequestBody ProjectDto projectDto) {
        ProjectDto createdProject = projectService.createProject(projectDto);
        ResponseDto<ProjectDto> response = ResponseDto.<ProjectDto>builder()
                .success(true)
                .message("Project created successfully")
                .data(createdProject)
                .status(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ProjectDto>> getProjectById(@PathVariable Long id) {
        ProjectDto project = projectService.getProjectById(id);
        ResponseDto<ProjectDto> response = ResponseDto.<ProjectDto>builder()
                .success(true)
                .message("Project retrieved successfully")
                .data(project)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/key/{key}")
    public ResponseEntity<ResponseDto<ProjectDto>> getProjectByKey(@PathVariable String key) {
        ProjectDto project = projectService.getProjectByKey(key);
        ResponseDto<ProjectDto> response = ResponseDto.<ProjectDto>builder()
                .success(true)
                .message("Project retrieved successfully")
                .data(project)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ProjectDto>>> getAllProjects() {
        List<ProjectDto> projects = projectService.getAllProjects();
        ResponseDto<List<ProjectDto>> response = ResponseDto.<List<ProjectDto>>builder()
                .success(true)
                .message("Projects retrieved successfully")
                .data(projects)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ProjectDto>> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectDto projectDto) {
        ProjectDto updatedProject = projectService.updateProject(id, projectDto);
        ResponseDto<ProjectDto> response = ResponseDto.<ProjectDto>builder()
                .success(true)
                .message("Project updated successfully")
                .data(updatedProject)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        ResponseDto<Void> response = ResponseDto.<Void>builder()
                .success(true)
                .message("Project deleted successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
