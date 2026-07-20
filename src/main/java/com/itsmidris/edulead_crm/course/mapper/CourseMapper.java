package com.itsmidris.edulead_crm.course.mapper;

import com.itsmidris.edulead_crm.course.dto.request.CreateCourseRequest;
import com.itsmidris.edulead_crm.course.dto.request.UpdateCourseRequest;
import com.itsmidris.edulead_crm.course.dto.response.CourseResponse;
import com.itsmidris.edulead_crm.course.dto.response.CourseSummaryResponse;
import com.itsmidris.edulead_crm.course.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", constant = "true")
    Course toEntity(CreateCourseRequest request);

    CourseResponse toResponse(Course course);

    CourseSummaryResponse toSummaryResponse(Course course);

    UpdateCourseRequest toUpdateRequest(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(UpdateCourseRequest request, @MappingTarget Course course);

}
