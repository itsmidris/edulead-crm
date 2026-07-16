package com.itsmidris.edulead_crm.course.controller;

import com.itsmidris.edulead_crm.common.payload.ApiResponse;
import com.itsmidris.edulead_crm.common.util.ResponseBuilder;
import com.itsmidris.edulead_crm.course.dto.request.CreateCourseRequest;
import com.itsmidris.edulead_crm.course.dto.response.CourseResponse;
import com.itsmidris.edulead_crm.course.dto.response.CourseSummaryResponse;
import com.itsmidris.edulead_crm.course.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.catalina.LifecycleState;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Course Management", description = "Manage available courses")
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Create Course", description = "Create a new course")
    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(@Valid @RequestBody CreateCourseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success("Course Created successfully", courseService.createCourse(request)));
    }

    @Operation(summary = "Get Course By Course Id", description = "Check course by course id.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseBuilder.success("Course fetched successfully.",courseService.getCourseById(id)));
    }

    @Operation(summary = "Get All Courses", description = "View all available courses.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses() {
        return ResponseEntity.ok(ResponseBuilder.success("Courses fetched successfully.",courseService.getAllCourses()));
    }

    @Operation(summary = "Get Active Course", description = "View current active courses.")
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<CourseSummaryResponse>>> getActiveCourses() {
        return ResponseEntity.ok(ResponseBuilder.success("Active courses fetched successfully",courseService.getActiveCourses()));
    }

    @Operation(summary = "Disable Course", description = "Deactivate a course and it will be available in system.")
    @PatchMapping("/{id}/disable")
    public ResponseEntity<ApiResponse<Valid>> disableCourse(@PathVariable Long id) {
        courseService.disableCourse(id);
        return ResponseEntity.ok(ResponseBuilder.success("Course disabled Successfully.",null));
    }

}
