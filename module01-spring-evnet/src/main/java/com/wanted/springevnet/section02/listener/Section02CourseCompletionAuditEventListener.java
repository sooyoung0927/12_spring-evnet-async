package com.wanted.springevnet.section02.listener;

import com.wanted.springevnet.section02.event.Section02CourseCompletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// 로그 찍는 리스너
@Component
public class Section02CourseCompletionAuditEventListener {

    /*comment
    *  해당 클래스는 1개의 이벤트가 발생했을 때 여러개의 리스너가 동작하는 것을 보여주기 위함이다
    *  해당 클래스는 이벤트가 발생하면 Audit(검사, 로깅) 처리하는 리스너 역할을 하게 된다 */

    private static final Logger log = LoggerFactory.getLogger(Section02CourseCompletionAuditEventListener.class);

    @EventListener
    public void eventHandle(Section02CourseCompletedEvent event){
        log.info("[section02] 감사 로그 기록 : userId={}, courseId={}, userName={}",
                event.courseId(),
                event.courseId(),
                event.userName());
    }
}
