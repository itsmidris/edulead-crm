package com.itsmidris.edulead_crm.course.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseResponse {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long id;
    private String courseName;
    private Integer duration;
    private String description;
    private boolean active;

}
