package com.wanted.springevnet.common.run;

import com.wanted.springevnet.course.entity.Course;
import com.wanted.springevnet.course.entity.CourseStatus;
import com.wanted.springevnet.course.repository.CourseRepository;
import com.wanted.springevnet.enrollment.entity.Enrollment;
import com.wanted.springevnet.enrollment.repository.EnrollmentRepository;
import com.wanted.springevnet.user.entity.User;
import com.wanted.springevnet.user.entity.UserStatus;
import com.wanted.springevnet.user.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class DemoScenarioFactory {
    // 테스트를 위한 더미데이터

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public DemoScenarioFactory(
            UserRepository userRepository,
            CourseRepository courseRepository,
            EnrollmentRepository enrollmentRepository
    ) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Transactional
    public DemoScenario createScenario() {
        String token = UUID.randomUUID().toString().substring(0, 8);

        User educator = userRepository.save(
                User.create(
                        "educator-" + token + "@lxp.local",
                        "hashed-password",
                        "교육자-" + token,
                        UserStatus.active
                )
        );

        Course course = courseRepository.save(
                Course.create(
                        educator,
                        "Spring Event 집중 과정",
                        "Spring Data JPA 기반 이벤트 처리 수업용 과정",
                        CourseStatus.published
                )
        );

//        Enrollment section01Enrollment = enrollmentRepository.save(createEnrollment(token, "learner01", course));
//        Enrollment section02Enrollment = enrollmentRepository.save(createEnrollment(token, "learner02", course));
        Enrollment section03Enrollment = enrollmentRepository.save(createEnrollment(token, "learner03", course));

        return new DemoScenario(
//                section01Enrollment.getEnrollmentId(),
//                section02Enrollment.getEnrollmentId()
                section03Enrollment.getEnrollmentId()
        );
    }

    private Enrollment createEnrollment(String token, String prefix, Course course) {
        User learner = userRepository.save(
                User.create(
                        prefix + "-" + token + "@lxp.local",
                        "hashed-password",
                        "수강생-" + prefix + "-" + token,
                        UserStatus.active
                )
        );

        return Enrollment.create(learner, course);
    }
}
