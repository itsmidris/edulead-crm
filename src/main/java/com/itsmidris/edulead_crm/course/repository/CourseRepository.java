package com.itsmidris.edulead_crm.course.repository;

import com.itsmidris.edulead_crm.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseName(String courseName);

    boolean existsByCourseName(String courseName);

    List<Course> findByActiveTrue();

    List<Course> findByCourseNameContainingIgnoreCase(String courseName);

    List<Course> findByActive(boolean active);

    List<Course> findByCourseNameContainingIgnoreCaseAndActive(String courseName, boolean active);
}
