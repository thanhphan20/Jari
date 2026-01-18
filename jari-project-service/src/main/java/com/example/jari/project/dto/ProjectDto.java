package com.example.jari.project.dto;

import com.example.jari.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto extends BaseDto {
    private String key;
    private String name;
    private String description;
    private String avatarUrl;
    private Long leadUserId;
    private boolean active;
}
