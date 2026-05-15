package com.wanted.springevnet.course.repository;

import com.wanted.springevnet.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
