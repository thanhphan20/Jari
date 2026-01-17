package com.example.jari.project.service;

import com.example.jari.common.exception.ResourceNotFoundException;
import com.example.jari.project.dto.ProjectDto;
import com.example.jari.project.entity.Project;
import com.example.jari.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = Project.builder()
                .key(projectDto.getKey())
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .avatarUrl(projectDto.getAvatarUrl())
                .leadUserId(projectDto.getLeadUserId())
                .active(true)
                .build();

        Project savedProject = projectRepository.save(project);
        return mapToDto(savedProject);
    }

    @Transactional(readOnly = true)
    public ProjectDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        return mapToDto(project);
    }

    @Transactional(readOnly = true)
    public ProjectDto getProjectByKey(String key) {
        Project project = projectRepository.findByKey(key)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with key: " + key));
        return mapToDto(project);
    }

    @Transactional(readOnly = true)
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setAvatarUrl(projectDto.getAvatarUrl());
        project.setLeadUserId(projectDto.getLeadUserId());
        project.setActive(projectDto.isActive());

        Project updatedProject = projectRepository.save(project);
        return mapToDto(updatedProject);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
        projectRepository.delete(project);
    }

    private ProjectDto mapToDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .key(project.getKey())
                .name(project.getName())
                .description(project.getDescription())
                .avatarUrl(project.getAvatarUrl())
                .leadUserId(project.getLeadUserId())
                .active(project.isActive())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .build();
    }
}
