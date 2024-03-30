package com.example.jari.service;

import com.example.jari.dto.TaskDto;
import com.example.jari.entity.Task;
import com.example.jari.mapper.TaskMapper;
import com.example.jari.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    public static Task createTask(TaskDto taskDto){
        Task newTask = new Task();
        newTask.setSummary(taskDto.getSummary());
        newTask.setType(taskDto.getType());
        newTask.setPriority(taskDto.getPriority());
        newTask.setOrder(taskDto.getOrder());
        newTask.setDescr(taskDto.getDescr());
        return newTask;
    }
}
