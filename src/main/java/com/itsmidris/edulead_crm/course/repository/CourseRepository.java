package com.itsmidris.edulead_crm.course.repository;

import com.itsmidris.edulead_crm.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseName(String courseName);

    boolean existsByCourseName(String courseName);

    List<Course> findByActiveTrue();
}
