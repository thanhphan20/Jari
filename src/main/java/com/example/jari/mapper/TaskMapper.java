package com.example.jari.mapper;

import com.example.jari.dto.TaskDto;
import com.example.jari.entity.Task;

public class TaskMapper {
    public static TaskDto mapTaskDto (Task task, TaskDto taskDto){
        taskDto.setOrder(task.getOrder());
        taskDto.setPriority(task.getPriority());
        taskDto.setType(task.getType());
        taskDto.setSummary(task.getSummary());
        taskDto.setDescr(task.getDescr());
        return taskDto;
    }

    public static Task mapTask (Task task, TaskDto taskDto){
        task.setOrder(taskDto.getOrder());
        task.setPriority(taskDto.getPriority());
        task.setType(taskDto.getType());
        task.setSummary(taskDto.getSummary());
        task.setDescr(taskDto.getDescr());
        return task;
    }

}
