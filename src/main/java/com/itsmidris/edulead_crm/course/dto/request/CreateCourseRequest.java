package com.itsmidris.edulead_crm.course.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class CreateCourseRequest {

    @NotBlank(message = "Course name is required")
    @Size(max = 100)
    private String courseName;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least of 1 years")
    private Integer duration;

    @Size(max = 500)
    private String description;
}
