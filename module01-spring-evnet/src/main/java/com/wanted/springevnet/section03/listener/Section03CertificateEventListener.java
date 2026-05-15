package com.wanted.springevnet.section03.listener;

import com.wanted.springevnet.certificate.service.CertificateService;
import com.wanted.springevnet.section03.event.Section03CourseCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;


// 수료증 발급하는 리스너
@Component
public class Section03CertificateEventListener {
    private static final Logger log = LoggerFactory.getLogger(Section03CertificateEventListener.class);

    private final CertificateService certificateService ;

    public Section03CertificateEventListener(CertificateService certificateService) {
        this.certificateService = certificateService;
    }


    /*comment
    *  Listener 클래스의 책임은 Event 를 전달받고, 언제 실행하며,
    *  어느 처리자에게 Event 를 넘길 지를 정하는 역할을 하게 된다
    *  ===
    *  AFTER_COMMIT 의 의미
    *  - 수강 완료 트랜잭션이 정상 커밋이 된 뒤에만 실행이 된다
    *  - 따라서 수강 완료 메서드가 롤백이 되면 해당 리스너는 동작하지 않는다
    *  ===
    *  왜 이런 것을 생각해야 하나 ?
    *  - 수료증 발급은 반드시 수강 완료가 DB에 INSERT 가 된 후에만 의미가 있다
    *  - 만약 수강 완료 트랜잭션이 롤백되었는데도 리스너가 동작한다면 데이터 정합성이 깨지게 된다
    *   */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void eventHandle(Section03CourseCompletedEvent event){
        // 강의 수강이 완료될 때의 event 정보가 event라는 변수에 담김

        certificateService.issueAfterCourseCompletion(
                event.enrollmentId(),
                event.courseTitle(),
                "AFTER-COMMIT"
        );
    }


}
