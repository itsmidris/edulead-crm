package com.itsmidris.edulead_crm.course.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String courseName;
    private Integer duration;
    private String description;
    private boolean active;

}
