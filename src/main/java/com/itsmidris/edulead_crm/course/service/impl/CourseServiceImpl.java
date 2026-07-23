package com.itsmidris.edulead_crm.course.service.impl;

import com.itsmidris.edulead_crm.common.exception.DuplicateResourceException;
import com.itsmidris.edulead_crm.common.exception.ResourceNotFoundException;
import com.itsmidris.edulead_crm.course.dto.request.CreateCourseRequest;
import com.itsmidris.edulead_crm.course.dto.request.UpdateCourseRequest;
import com.itsmidris.edulead_crm.course.dto.response.CourseResponse;
import com.itsmidris.edulead_crm.course.dto.response.CourseSummaryResponse;
import com.itsmidris.edulead_crm.course.entity.Course;
import com.itsmidris.edulead_crm.course.mapper.CourseMapper;
import com.itsmidris.edulead_crm.course.repository.CourseRepository;
import com.itsmidris.edulead_crm.course.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseResponse createCourse(CreateCourseRequest request) {

        if (courseRepository.existsByCourseName(request.getCourseName())) {
            throw new DuplicateResourceException("Course already exists.");
        }

        Course course = courseMapper.toEntity(request);

        Course savedCourse = courseRepository.save(course);

        return courseMapper.toResponse(savedCourse);

    }

    @Override
    public CourseResponse getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found with id : " + id));
        return courseMapper.toResponse(course);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream().map(courseMapper::toResponse).toList();
    }

    @Override
    public List<CourseSummaryResponse> getActiveCourses() {
        return courseRepository.findByActiveTrue().stream().map(courseMapper::toSummaryResponse).toList();
    }

    @Override
    public void disableCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course not found with id: "+ id));
        course.setActive(false);
        courseRepository.save(course);
    }

    @Override
    public UpdateCourseRequest getCourseForUpdate(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found."));
        return courseMapper.toUpdateRequest(course);
    }

    @Override
    public CourseResponse updateCourse(Long id, UpdateCourseRequest request) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found."));
        if (!course.getCourseName().equals(request.getCourseName()) && courseRepository.existsByCourseName(request.getCourseName())) {
            throw new DuplicateResourceException("Course already exists.");
        }
        courseMapper.updateEntityFromRequest(request, course);
        Course updatedCourse = courseRepository.save(course);
        return courseMapper.toResponse(updatedCourse);
    }

    @Override
    public List<CourseResponse> searchCourses(String keyword, Boolean active) {
        keyword = (keyword == null || keyword.isBlank())
                ? null
                : keyword.trim();
        List<Course> courses;
        if (keyword == null && active == null) {
            courses = courseRepository.findAll();
        } else if (keyword != null && active == null) {
            courses = courseRepository.findByCourseNameContainingIgnoreCase(keyword);
        } else if (keyword == null) {
            courses = courseRepository.findByActive(active);
        } else {
            courses = courseRepository.findByCourseNameContainingIgnoreCaseAndActive(keyword, active);
        }
        return courses.stream().map(courseMapper::toResponse).toList();
    }
}
