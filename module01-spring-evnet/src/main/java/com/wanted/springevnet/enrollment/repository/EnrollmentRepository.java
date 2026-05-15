package com.wanted.springevnet.enrollment.repository;

import com.wanted.springevnet.enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

    // 수강id로 사용자와 코스 찾기
    // @EntityGraph
    // Entity 간의 연관관계가 있어야만 사용 가능
    // 연관관계를 통한 엔티티 탐색을 하는 것으로
    // 지금 enrollmentId를 기준으로 user와 course 데이터를 탐색할 수 있다
    @EntityGraph(attributePaths = {"user","course"})
    Optional<Enrollment>  findWithUserAndCourseByEnrollmentId(Long enrollmentId);
}
