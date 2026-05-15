package com.wanted.springevnet.section03.service;

import com.wanted.springevnet.enrollment.entity.Enrollment;
import com.wanted.springevnet.enrollment.repository.EnrollmentRepository;
import com.wanted.springevnet.section02.event.Section02CourseCompletedEvent;
import com.wanted.springevnet.section03.event.Section03CourseCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class Section03CourseCompletionService {

    private final EnrollmentRepository enrollmentRepository;
    // Event 발행자
    private final ApplicationEventPublisher publisher;

    // 생성자로 의존성 주입
    public Section03CourseCompletionService(EnrollmentRepository enrollmentRepository, ApplicationEventPublisher publisher) {
        this.enrollmentRepository = enrollmentRepository;
        this.publisher = publisher;
    }


    /*comment
    *  Section03 의 실행 시나리오
    *  publishEvent() 를 호출하는 시점은 @Transactional 내부에 감싸져 있다
    *  단순히 이벤트를 분리하고 끝내는 것이 아니라 후속 작업(Event) 의 실행 시점을 제어하는 것이 중요하다
    *  DB 커밋이 된 이후에 실행이 되어야 안전한 Event 작업은
    *  리스너 쪽에서 @TransactionalEventListener(AFTER_COMMIT) 으로 분리한다
    *  */
    @Transactional
    public void completeCourse(Long enrollmentId){
        Enrollment enrollment = enrollmentRepository.findWithUserAndCourseByEnrollmentId(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("수강 정보를 찾을 수 없습니다"));

        // 수강 완료
        enrollment.complete();

        log.info("[section03] Course 서비스는 수간 완료 후 이벤트를 발행한다.");

        publisher.publishEvent(
                new Section03CourseCompletedEvent(
                        enrollment.getEnrollmentId(),
                        enrollment.getCourse().getTitle()
                )
        );
    }



}
