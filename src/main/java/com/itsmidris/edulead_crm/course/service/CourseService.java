package com.itsmidris.edulead_crm.course.service;

import com.itsmidris.edulead_crm.course.dto.request.CreateCourseRequest;
import com.itsmidris.edulead_crm.course.dto.request.UpdateCourseRequest;
import com.itsmidris.edulead_crm.course.dto.response.CourseResponse;
import com.itsmidris.edulead_crm.course.dto.response.CourseSummaryResponse;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request);

    CourseResponse getCourseById(Long id);

    List<CourseResponse> getAllCourses();

    List<CourseSummaryResponse> getActiveCourses();

    void disableCourse(Long id);

    UpdateCourseRequest getCourseForUpdate(Long id);

    CourseResponse updateCourse(Long id, UpdateCourseRequest request);

}
