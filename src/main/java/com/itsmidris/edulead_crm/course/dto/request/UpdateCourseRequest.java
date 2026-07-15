package com.itsmidris.edulead_crm.course.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourseRequest {

    @NotBlank(message = "Course name is required.")
    @Size(max = 100)
    private String courseName;

    @NotNull(message = "Duration is required.")
    @Min(value = 1)
    private Integer duration;

    @Size(max = 500)
    private String description;

    @NotNull
    private Boolean active;
}
